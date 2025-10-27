package com.lopez_jorge.rutas_temas.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lopez_jorge.rutas_temas.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
   var textState by remember { mutableStateOf(TextFieldValue("")) }

   Scaffold(
      topBar = {
         TopAppBar(title = { Text("First Screen") })
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
         Text(text = "App para navegar")
         Spacer(modifier = Modifier.height(16.dp))

         TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Ingresa un nombre") }
         )

         Spacer(modifier = Modifier.height(24.dp))

         Button(
            onClick = {
               val name = textState.text
               if (name.isNotEmpty()) {
                  navController.navigate(Routes.SecondScreen.createRoute(name))
               }
            }
         ) {
            Text("Navega a la segunda ventana")
         }
      }
   }
}
