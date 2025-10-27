package com.lopez_jorge.rutas_temas.navigation

sealed class Routes(val route: String) {
    object SplashScreen: Routes("splash_screen")
    object FirstScreen: Routes("first_screen")
    object SecondScreen: Routes("second_screen?name={name}"){
        fun createRoute(name: String)="second_screen?name=$name"
    }
}