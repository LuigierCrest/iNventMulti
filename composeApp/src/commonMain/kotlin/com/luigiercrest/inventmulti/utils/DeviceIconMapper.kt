package com.luigiercrest.inventmulti.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.DesktopMac
import androidx.compose.material.icons.filled.DeveloperBoard
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material.icons.filled.Monitor
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Tablet
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.ui.graphics.vector.ImageVector

object DeviceIconMapper {
    fun getIcon(deviceType: String?): ImageVector {
        return when (deviceType?.trim()?.lowercase()) {
            "pdi/pim" -> Icons.Filled.DeveloperBoard
            "miniportátil" -> Icons.Filled.LaptopChromebook
            "ordenador" -> Icons.Filled.DesktopMac
            "escáner" -> Icons.Filled.Scanner
            "audiovisuales" -> Icons.Filled.Speaker
            "impresora" -> Icons.Filled.Print
            "robótica" -> Icons.Filled.Android
            "portátil" -> Icons.Filled.Laptop
            "monitor" -> Icons.Filled.Monitor
            "disco duro" -> Icons.Filled.Storage
            "webcam" -> Icons.Filled.Videocam
            "redes" -> Icons.Filled.Router
            "carros" -> Icons.Filled.ShoppingCart
            "proyector" -> Icons.Filled.DeveloperMode
            "tablet" -> Icons.Filled.Tablet
            else -> Icons.Filled.Devices
        }
    }
}