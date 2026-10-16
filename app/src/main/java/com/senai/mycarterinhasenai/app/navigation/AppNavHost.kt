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
import com.senai.mycarterinhasenai.feature.professor.presentation.ProfessorViewModel
import com.senai.mycarterinhasenai.feature.professor.presentation.factory.ProfessorViewModelFactory
import com.senai.mycarterinhasenai.feature.professor.presentation.screen.TurmasProfessorScreen
import com.senai.mycarterinhasenai.feature.professor.presentation.screen.UcsProfessorScreen
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(navController:NavHostController,darkTheme:Boolean,onDarkThemeChange:(Boolean)->Unit,container:AppContainer){
 val session:SessionViewModel=viewModel(factory=remember(container){SessionViewModelFactory(container.sessionTokenStore)})
 val usuario by session.usuarioLogado.collectAsStateWithLifecycle()
 val professor=usuario?.perfil?.equals("PROFESSOR",ignoreCase=true)==true

 fun logout(){
  session.limparSessao()
  navController.navigate(Routes.Login.route){
   popUpTo(navController.graph.id){inclusive=true}
   launchSingleTop=true
  }
 }

 NavHost(navController=navController,startDestination=Routes.Login.route){
  composable(Routes.Login.route){
   val vm:LoginViewModel=viewModel(factory=remember(container){LoginViewModelFactory(container.loginRepository)})
   LoginScreen(viewModel=vm,onLoginSucesso={u->
    session.setUsuarioLogado(u)
    navController.navigate(if(u.perfil.equals("PROFESSOR",true)) Routes.HomeProfessor.route else Routes.HomeAluno.route){
     popUpTo(Routes.Login.route){inclusive=true}
     launchSingleTop=true
    }
   })
  }
  composable(Routes.HomeAluno.route){
   val u=usuario
   if(u==null || professor){RedirecionarParaLogin(navController)}
   else AppShell("Início",u,false,darkTheme,onDarkThemeChange,{},::logout){padding->
    HomeScreen(navController,u,Modifier.fillMaxSize().padding(padding))
   }
  }
  composable(Routes.Carteirinha.route){
   val u=usuario
   if(u==null || professor){RedirecionarParaLogin(navController)}
   else AppShell("Carteirinha",u,true,darkTheme,onDarkThemeChange,{navController.popBackStack()},::logout){padding->
    CarteirinhaScreen(u,Modifier.fillMaxSize().padding(padding))
   }
  }
  composable(Routes.UCAluno.route){
   val u=usuario
   if(u==null || professor){RedirecionarParaLogin(navController)}
   else {
    val vm:UnidadeCurricularViewModel=viewModel(factory=remember(container){UnidadeCurricularViewModelFactory(container.unidadeCurricularRepository)})
    AppShell("Unidades Curriculares",u,true,darkTheme,onDarkThemeChange,{navController.popBackStack()},::logout){padding->
     UnidadeCurricularScreen(viewModel=vm,modifier=Modifier.fillMaxSize().padding(padding))
    }
   }
  }
  composable(Routes.HomeProfessor.route){
   val u=usuario
   if(u==null || !professor){RedirecionarParaLogin(navController)}
   else AppShell("Portal Professor",u,false,darkTheme,onDarkThemeChange,{},::logout){padding->
    HomeScreen(navController,u,Modifier.fillMaxSize().padding(padding))
   }
  }
  composable(Routes.TurmasProfessor.route){
   val u=usuario
   if(u==null || !professor){RedirecionarParaLogin(navController)}
   else {
    val vm:ProfessorViewModel=viewModel(factory=remember(container){ProfessorViewModelFactory(container.professorRepository)})
    AppShell("Minhas Turmas",u,true,darkTheme,onDarkThemeChange,{navController.popBackStack()},::logout){padding->
     TurmasProfessorScreen(vm,Modifier.fillMaxSize().padding(padding))
    }
   }
  }
  composable(Routes.UCProfessor.route){
   val u=usuario
   if(u==null || !professor){RedirecionarParaLogin(navController)}
   else {
    val vm:ProfessorViewModel=viewModel(factory=remember(container){ProfessorViewModelFactory(container.professorRepository)})
    AppShell("UCs do Professor",u,true,darkTheme,onDarkThemeChange,{navController.popBackStack()},::logout){padding->
     UcsProfessorScreen(vm,Modifier.fillMaxSize().padding(padding))
    }
   }
  }
 }
}

@Composable
private fun RedirecionarParaLogin(navController:NavHostController){
 LaunchedEffect(Unit){
  navController.navigate(Routes.Login.route){
   popUpTo(navController.graph.id){inclusive=true}
   launchSingleTop=true
  }
 }
}
