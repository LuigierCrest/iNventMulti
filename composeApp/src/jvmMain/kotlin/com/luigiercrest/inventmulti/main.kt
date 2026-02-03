package com.luigiercrest.inventmulti

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.luigiercrest.inventmulti.di.appModule
import com.luigiercrest.presentation.platformJvmModule
import org.koin.core.context.startKoin

fun main() = application {

    // iniciar Koin antes de cualquier Composable que lo use
    startKoin {
        modules(appModule,platformJvmModule) // sustituir por tus módulos reales
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "INventMulti",
    ) {
        App()
    }
}