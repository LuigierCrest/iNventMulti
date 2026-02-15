package com.luigiercrest.data.dto

data class AsignacionDTO (
    val idAsignacion: Int,
    val idUsuario: Int,
    val idProveedor: Int,
    val entrega: String // FECHA
) {
}