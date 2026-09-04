package com.senai.mycarterinhasenai.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.mycarterinhasenai.app.session.SessionViewModel
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen.CarterinhaScreen
import com.senai.mycarterinhasenai.feature.home.presentation.screen.HomeScreen
import com.senai.mycarterinhasenai.feature.login.presention.screen.LoginScreen
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel = viewModel()


) {
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {


        composable(Routes.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSuceso = {
                    usuario ->
                    sessionViewModel.setUsuarioLogado(usuario)
                    navController.navigate(Routes.Home.route)
                }
            )
        }


        composable(Routes.Home.route) {
            val usuario = usuarioLogado
            if(usuario==null){
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login.route)
                }
            }else
            HomeScreen(navController = navController)
        }



        composable(Routes.Carterinha.route) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                CarterinhaScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }



        composable(Routes.UnidadeCurricular.route) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                UnidadeCurricularScreen(
                    modifier = Modifier.padding(innerPadding)

                )
            }
        }
    }
}