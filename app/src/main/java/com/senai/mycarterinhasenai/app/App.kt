package com.senai.mycarterinhasenai.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.senai.mycarterinhasenai.app.navigation.AppNavHost
import com.senai.mycarterinhasenai.core.designsystem.theme.MyCarterinhaSenaiTheme

@Composable
fun App(){
    MyCarterinhaSenaiTheme() {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController,
        )
    }
}