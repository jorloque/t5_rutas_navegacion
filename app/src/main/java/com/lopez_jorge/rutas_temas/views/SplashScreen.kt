package com.lopez_jorge.rutas_temas.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.lopez_jorge.rutas_temas.R
import com.lopez_jorge.rutas_temas.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        // Simulación de carga o inicialización
        delay(5000)
        // Elimina el splash de la pila para no volver atrás
        navController.popBackStack()
        navController.navigate(Routes.FirstScreen.route)
    }

    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp, 150.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "Bienvenidos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green // tu color personalizado
        )
    }
}
