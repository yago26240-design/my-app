package com.senai.mycarterinhasenai.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.mycarterinhasenai.feature.carterinha.presentantion.screen.CarterinhaScreen
import com.senai.mycarterinhasenai.feature.home.presentation.screen.HomeScreen
import com.senai.mycarterinhasenai.feature.login.presention.screen.LoginScreen
import com.senai.mycarterinhasenai.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {


        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }


        composable(Routes.Home.route) {
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

                )
            }
        }
    }
}