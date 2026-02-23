package com.luigiercrest.inventmulti.scanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun BarcodeScannerScreen(
    onScanned: (String) -> Unit,
    onCancelled: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Escáner no disponible en escritorio\nPulse Esc para salir")
    }
}