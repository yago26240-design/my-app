package com.senai.mycarterinhasenai.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.mycarterinhasenai.app.di.AppContainer
import com.senai.mycarterinhasenai.app.session.SessionViewModel
import com.senai.mycarterinhasenai.app.session.SessionViewModelFactory
import com.senai.mycarterinhasenai.app.ui.AppShell
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen.CarteirinhaScreen
import com.senai.mycarterinhasenai.feature.home.presentation.screen.HomeScreen
import com.senai.mycarterinhasenai.feature.login.presention.LoginViewModel
import com.senai.mycarterinhasenai.feature.login.presention.factory.LoginViewModelFactory
import com.senai.mycarterinhasenai.feature.login.presention.screen.LoginScreen
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen
import com.senai.mycarterinhasenai.feature.professor.turmas.presentation.TurmaProfessorViewModel
import com.senai.mycarterinhasenai.feature.professor.turmas.presentation.factory.TurmaProfessorViewModelFactory
import com.senai.mycarterinhasenai.feature.professor.turmas.presentation.screen.TurmaProfessorScreen
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.UnidadeCurricularProfessorViewModel
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.factory.UnidadeCurricularProfessorViewModelFactory
import com.senai.mycarterinhasenai.feature.professor.unidadecurriculares.presentation.screen.UnidadeCurricularProfessorScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    container: AppContainer
) {
    val sessionFactory = remember(container.sessionTokenStore) {
        SessionViewModelFactory(
            sessionTokenStore = container.sessionTokenStore
        )
    }

    val sessionViewModel: SessionViewModel = viewModel(factory = sessionFactory)
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()
    val usuario = usuarioLogado

    fun logout() {
        sessionViewModel.limparSessao()
        navController.navigate(Routes.Login.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            val loginFactory = remember(
                container.loginRepository
            ) {
                LoginViewModelFactory(
                    repository = container.loginRepository
                )
            }

            val loginViewModel: LoginViewModel = viewModel(factory = loginFactory)

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSucesso = { usuario ->
                    sessionViewModel.setUsuarioLogado(usuario)
                    navController.navigate(if (usuario.perfil.equals("PROFESSOR", ignoreCase = true)) Routes.HomeProfessor.route else Routes.HomeAluno.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            Routes.HomeAluno.route
        ) {
            if (usuario == null || usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { },
                    onLogout = {
                        logout()
                    }
                ) { innerPadding ->
                    HomeScreen(
                        navController = navController,
                        usuarioLogado = usuario,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.Carteirinha.route) {
            if (usuario == null || usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                AppShell(
                    title = "Carteirinha",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = {
                        logout()
                    }
                ) { innerPadding ->
                    CarteirinhaScreen(
                        usuarioLogado = usuario,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.UCAluno.route) {
            if (usuario == null || usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                val ucFactory = remember(container.unidadeCurricularRepository) {
                    UnidadeCurricularViewModelFactory(
                        repository = container.unidadeCurricularRepository
                    )
                }
                val ucViewModel: UnidadeCurricularViewModel = viewModel(factory = ucFactory)

                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = {
                        logout()
                    }
                ) { innerPadding ->
                    UnidadeCurricularScreen(
                        viewModel = ucViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.HomeProfessor.route) {
            if (usuario == null || !usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { },
                    onLogout = { logout() }
                ) { innerPadding ->
                    HomeScreen(
                        navController = navController,
                        usuarioLogado = usuario,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.TurmasProfessor.route) {
            if (usuario == null || !usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                val turmasFactory = remember(container.turmaProfessorRepository) {
                    TurmaProfessorViewModelFactory(
                        repository = container.turmaProfessorRepository
                    )
                }
                val turmasViewModel: TurmaProfessorViewModel = viewModel(factory = turmasFactory)

                AppShell(
                    title = "Turmas",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = { logout() }
                ) { innerPadding ->
                    TurmaProfessorScreen(
                        viewModel = turmasViewModel,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.UCProfessor.route) {
            if (usuario == null || !usuario.perfil.equals("PROFESSOR", ignoreCase = true)) {
                RedirecionarParaLogin(navController)
            } else {
                val ucProfessorFactory = remember(container.unidadeCurricularProfessorRepository) {
                    UnidadeCurricularProfessorViewModelFactory(
                        repository = container.unidadeCurricularProfessorRepository
                    )
                }
                val ucProfessorViewModel: UnidadeCurricularProfessorViewModel =
                    viewModel(factory = ucProfessorFactory)

                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = { logout() }
                ) { innerPadding ->
                    UnidadeCurricularProfessorScreen(
                        viewModel = ucProfessorViewModel,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun RedirecionarParaLogin(
    navController:NavHostController
) {
    LaunchedEffect(Unit) {
        navController.navigate( Routes.Login.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}