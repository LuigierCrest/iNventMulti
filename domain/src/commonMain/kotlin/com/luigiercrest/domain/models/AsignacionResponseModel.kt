package com.luigiercrest.domain.models

data class AsignacionResponseModel(
    val idAsignacion: Int?,
    val idCentro: Int?,
    val idProveedor: Int?,
    val entrega: String?, //FECHA
    val statusCode: Int?,
)
