package com.lopez_jorge.rutas_temas.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, name: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Second Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("He navegado")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Parámetro: $name")

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver atrás")
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🌈 Animación de color
            AnimatedColorExample()

            Spacer(modifier = Modifier.height(40.dp))

            // 🌫️ Animación de opacidad
            AnimatedAlphaExample()
        }
    }
}

@Composable
fun AnimatedColorExample() {
    var animateColor by rememberSaveable { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF),
        label = "backgroundColorAnimation"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { animateColor = !animateColor }) {
            Text(text = "Cambiar Color")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hola Rick!",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(backgroundColor)
                .padding(20.dp)
        )
    }
}

@Composable
fun AnimatedAlphaExample() {
    var animateAlpha by rememberSaveable { mutableStateOf(true) }
    val alpha by animateFloatAsState(
        targetValue = if (animateAlpha) 1f else 0.3f,
        label = "alphaAnimation"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { animateAlpha = !animateAlpha }) {
            Text(text = "Cambiar Opacidad")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .graphicsLayer(alpha = alpha)
                .fillMaxWidth()
                .background(Color(0xFFFFA020))
        ) {
            Text(
                text = "Hola Rick!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
