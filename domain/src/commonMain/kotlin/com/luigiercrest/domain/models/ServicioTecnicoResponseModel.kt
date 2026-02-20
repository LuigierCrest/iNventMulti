package com.luigiercrest.domain.models

data class ServicioTecnicoResponseModel (
    val idServicioTecnico: Int?,
    val nombre: String?,
    val direccion: String?,
    val telefono: Int?,
    val email: String?,
    val statusCode: Int?
    ) {
}