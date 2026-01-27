package com.luigiercrest.inventmulti

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme{

        LoginScreen()
    }

//    MaterialTheme {
//
////        var showContent by remember { mutableStateOf(false) }
////        Column(
////            modifier = Modifier
////                .background(MaterialTheme.colorScheme.primaryContainer)
////                .safeContentPadding()
////                .fillMaxSize(),
////            horizontalAlignment = Alignment.CenterHorizontally,
////        ) {
////            Text ("InventMulti works!")
////
//////            Button(onClick = { showContent = !showContent }) {
//////                Text("Click me!")
//////            }
//////            AnimatedVisibility(showContent) {
//////                Text("Hola amigo!")
////////                val greeting = remember { Greeting().greet() }
////////                Column(
////////                    modifier = Modifier.fillMaxWidth(),
//////
////////                    horizontalAlignment = Alignment.CenterHorizontally,
////////                ) {
////////                    Image(painterResource(Res.drawable.compose_multiplatform), null)
////////                    Text("Compose: $greeting")
////////                }
//////            }
////        }
//    }
}