package com.luigiercrest.inventmulti.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val idCategoria: Int,
    val categoria: String,
    val descripcion: String,
    val iconName: String = "default"

){
}