package com.lopez_jorge.rutas_temas.views

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
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

            // 🧩 Nuevo: animación de expansión con animateContentSize
            ExpandableTextExample()

            Spacer(modifier = Modifier.height(40.dp))

            // 🌈 Animación de color
            AnimatedColorExample()
            Spacer(modifier = Modifier.height(40.dp))

            // 🌫️ Animación de opacidad
            AnimatedAlphaExample()
            Spacer(modifier = Modifier.height(40.dp))

            // 👀 Animación de visibilidad
            AnimatedVisibilityExample()
            Spacer(modifier = Modifier.height(40.dp))

            // 🔢 Animación de contador
            AnimatedCounterExample()
            Spacer(modifier = Modifier.height(40.dp))

            // 🔄 Crossfade simple (clic)
            CrossfadeExample()
            Spacer(modifier = Modifier.height(40.dp))

            // 🧭 Crossfade con botones
            CrossfadeMenuExample()
            Spacer(modifier = Modifier.height(40.dp))


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

@Composable
fun AnimatedVisibilityExample() {
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { visible = !visible }) {
            Text(text = if (visible) "Ocultar" else "Mostrar")
        }
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = "Hola Rick!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(0xFFFFA020))
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounterExample() {
    var times by rememberSaveable { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { times++ }) { Text("+1") }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = { times-- }) { Text("-1") }

        Spacer(modifier = Modifier.width(10.dp))

        AnimatedContent(targetState = times, label = "") {
            Text("Cuenta $it", modifier = Modifier.padding(10.dp))
        }

        AnimatedContent(
            targetState = times,
            transitionSpec = {
                if (targetState < initialState) {
                    (slideInVertically { height -> height } + fadeIn())
                        .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                } else {
                    (slideInVertically { height -> -height } + fadeIn())
                        .togetherWith(slideOutVertically { height -> height } + fadeOut())
                }
            },
            label = ""
        ) { targetCount ->
            Text("$targetCount", modifier = Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CrossfadeExample() {
    var visibleContent by rememberSaveable { mutableStateOf("Manga") }

    Crossfade(
        targetState = visibleContent,
        label = "",
        modifier = Modifier
            .clickable {
                visibleContent =
                    if (visibleContent == "Manga") "Anime" else "Manga"
            }
            .padding(20.dp)
    ) { screen ->
        when (screen) {
            "Manga" -> Text("Manga", fontWeight = FontWeight.Bold, color = Color(0xFF40C0FF))
            "Anime" -> Text("Anime", fontWeight = FontWeight.Bold, color = Color(0xFFFFA020))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CrossfadeMenuExample() {
    val sections = arrayOf("Anime", "Manga", "Otros")
    var visibleContent by rememberSaveable { mutableStateOf(sections[0]) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (section in sections) {
                Button(onClick = { visibleContent = section }) {
                    Text(section)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Crossfade(targetState = visibleContent, label = "") { screen ->
            when (screen) {
                "Manga" -> Text("Mostrar Manga")
                "Anime" -> Text("Mostrar Anime")
                "Otros" -> Text("Mostrar otros")
            }
        }
    }
}

@Composable
fun ExpandableTextExample() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = LinearEasing
                )
            )
            .background(Color(0xFFFFA0C0))
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Son Goku", fontWeight = FontWeight.Bold)
            TextButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Menos." else "Leer más...")
            }
        }

        Text(text = "Hey!")

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Goku niño: Un pequeño con cola tiene una habilidad innata para convertirse " +
                        "en un mono gigante sin capacidad de razonamiento y que destroza todo lo que esté a su paso. " +
                        "Sus principales enemigos derrotados fueron Pilaf, los soldados del Ejército Rojo y Piccolo Daimao.\n\n" +
                        "Goku joven: Milk se casa con Goku y tienen a Gohan. Después de algunos años, su hermano Raditz llega " +
                        "a la Tierra con la misión de colonizarla, y Goku pierde la vida por primera vez.\n\n" +
                        "Goku adulto: Tal vez el momento más importante. En pocos años, Goku alcanza los niveles de " +
                        "Super Saiyajin 1, 2, 3 y las fases de Dios.\n\n" +
                        "Después de la llegada de Beerus, los rituales de transformación de Kakaroto superan lo místico hasta alcanzar " +
                        "un poder a la altura del mismo creador del universo.",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
