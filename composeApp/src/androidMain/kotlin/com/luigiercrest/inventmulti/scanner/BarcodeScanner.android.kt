package com.luigiercrest.inventmulti.scanner

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import org.ncgroup.kscan.BarcodeFormat
import org.ncgroup.kscan.BarcodeResult
import org.ncgroup.kscan.ScannerController
import org.ncgroup.kscan.ScannerView
import org.ncgroup.kscan.scannerColors

@Composable
actual fun BarcodeScannerScreen(
    onScanned: (String) -> Unit,
    onCancelled: () -> Unit
) {
    // Permisos para la cámara
    val context = LocalContext.current
    // Recuerda el permiso
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }
    // Lanzador para los permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) {
            onCancelled()
        }
    }
    // si no hay permisos se lanza el diálogo
    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
    // si no hay permisos se muestra el mensaje
    if (!hasCameraPermission) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Se necesita permiso de cámara para escanear")
        }
        return
    }

    val controller = remember { ScannerController() }
    var hasScanned by remember { mutableStateOf(false) }

    ScannerView(
        modifier = Modifier.fillMaxSize(),
        codeTypes = listOf(BarcodeFormat.FORMAT_ALL_FORMATS),
        colors = scannerColors(),
        scannerController = controller,
        result = { barcodeResult ->
            if (!hasScanned) {
                when (barcodeResult) {
                    is BarcodeResult.OnSuccess -> {
                        hasScanned = true
                        val scannedValue = barcodeResult.barcode.data
                        onScanned(scannedValue)
                    }
                    is BarcodeResult.OnFailed -> {
                        println("LOG - Error escaneando: ${barcodeResult.exception.message}")
                    }
                    is BarcodeResult.OnCanceled -> {
                        hasScanned = true
                        onCancelled()
                    }
                }
            }
        }
    )
}