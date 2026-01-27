package com.luigiercrest.domain.models

data class LoginResponseModel(
    val token: String?,
    val expiresIn: Int?,
    val rol: String?,
    val idCentro: Int?,
    val idUsuario: Int?
)

