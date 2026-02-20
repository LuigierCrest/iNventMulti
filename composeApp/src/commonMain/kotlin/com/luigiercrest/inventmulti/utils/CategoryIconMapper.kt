package com.luigiercrest.inventmulti.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

object CategoryIconMapper {
    fun getIcon(categoryId: Int): ImageVector {
        return when (categoryId) {
            //"centros"
            1 -> Icons.Default.School
            //"proveedores"
            2 -> Icons.Default.LocalShipping
            //"servicios"
            3 -> Icons.Default.Build
            //"asignaciones"
            4 -> Icons.Default.CardGiftcard
            //"usuarios"
            5, 8 -> Icons.Default.Group
            //"dispositivos"
            6, 9 -> Icons.Default.Devices
            //"incidencias"
            7, 10 -> Icons.Default.Bolt
            else -> Icons.Default.Bolt
        }
    }
}