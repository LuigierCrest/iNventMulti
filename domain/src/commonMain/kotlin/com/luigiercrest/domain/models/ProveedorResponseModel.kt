package com.luigiercrest.domain.models

data class ProveedorResponseModel (
    val idProveedor: Int?,
    val nombre: String?,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val statusCode: Int?
    ) {
}