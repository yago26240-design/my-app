package com.senai.mycarterinhasenai.app.navigation

sealed class Routes (val route: String){

    data object Login : Routes(route = "login")

    data object Home : Routes(route = "home")

    object UnidadeCurricular : Routes("unidade_curricular")
    data object Carterinha : Routes (route = "carterinha")




}

