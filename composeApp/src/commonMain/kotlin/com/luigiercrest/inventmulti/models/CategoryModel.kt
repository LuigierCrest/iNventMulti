package com.luigiercrest.inventmulti.models

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