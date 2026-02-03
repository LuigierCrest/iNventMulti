package com.luigiercrest.inventmulti

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.luigiercrest.inventmulti.navigation.NavRoot

@Composable
fun App() {
    //setSingletonImageLoaderFactory {}
    MaterialTheme {
        NavRoot()
    }
}
