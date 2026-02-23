package com.luigiercrest.inventmulti.scanner

import androidx.compose.runtime.Composable

@Composable
expect fun BarcodeScannerScreen(
    onScanned: (String) -> Unit,
    onCancelled: () -> Unit
    // Solo disponible para Android e iOS
    // Repositorio: https://github.com/ismai117/KScan/
)