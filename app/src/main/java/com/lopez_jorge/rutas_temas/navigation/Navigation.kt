package com.lopez_jorge.rutas_temas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lopez_jorge.rutas_temas.viewa.SecondScreen
import com.lopez_jorge.rutas_temas.views.FirstScreen


@Composable
fun Navigation() {
    //Constante para gestionar el estado y se debe propagar entre todas las pantallas
    val navController = rememberNavController()

    //Elemento que conoce las diferentes pantallas y cual es la primera en lanzarse
    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen.route
    ){
        //Definición de la primera pantalla
        composable(
            route = Routes.FirstScreen.route
        ){
            FirstScreen(navController)
        }

        //Definición segunda pantalla que recibe un parámetro de tipo String
        composable(
            route = Routes.SecondScreen.route,
            arguments = listOf(
                navArgument(name = "name"){
                    defaultValue =""
                    type= NavType.StringType
                }
            )
        ){
            val argument = it.arguments?.getString("name")
            requireNotNull(argument)
            SecondScreen(navController, argument)
        }
    }
}
