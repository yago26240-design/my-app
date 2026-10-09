package com.senai.mycarterinhasenai.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.senai.mycarterinhasenai.app.di.AppContainer
import com.senai.mycarterinhasenai.app.navigation.AppNavHost
import com.senai.mycarterinhasenai.core.designsystem.theme.MyCarterinhaSenaiTheme

@Composable
fun App(container: AppContainer) {

    val systemDarkTheme = isSystemInDarkTheme()
    var darkTheme by rememberSaveable { mutableStateOf(systemDarkTheme) }

    MyCarterinhaSenaiTheme(
        darkTheme = darkTheme
    ) {
        val navController = rememberNavController()

        AppNavHost(
            navController = navController,
            darkTheme = darkTheme,
            onDarkThemeChange = { darkTheme = it },
            container = container
        )
    }
}