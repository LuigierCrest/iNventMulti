package com.luigiercrest.domain.models

data class CentroResponseModel (
    val idCentro: Int?,
    val tipo: String?,
    val nombre: String?,
    val direccion: String?,
    val municipio: String?,
    val statusCode: Int?
    ) {
}