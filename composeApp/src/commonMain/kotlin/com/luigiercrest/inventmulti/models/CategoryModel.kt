package com.luigiercrest.inventmulti.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val idCategoria: Int,
    val categoria: String,
    val descripcion: String,
    val iconName: String = "default"

){

//    fun getIcon(): ImageVector {
//        return when (iconName) {
//            "centros" -> Icons.Default.School
//            "proveedores" -> Icons.Default.LocalShipping
//            "servicios" -> Icons.Default.Build
//            "asignaciones" -> Icons.Default.CardGiftcard
//            "usuarios" -> Icons.Default.Group
//            "dispositivos" -> Icons.Default.Devices
//            "incidencias" -> Icons.Default.Bolt
//            else -> Icons.Default.Bolt
//
//        }
//    }
}