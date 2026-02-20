package com.luigiercrest.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class AsignacionDTO (
    val idAsignacionCompra: Int,
    val idCentro: Int,
    val idProveedor: Int,
    val entrega: String? // FECHA
) {
}