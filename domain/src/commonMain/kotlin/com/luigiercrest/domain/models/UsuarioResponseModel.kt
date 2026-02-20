package com.luigiercrest.domain.models

data class UsuarioResponseModel(
    val idUsuario: Int?,
    val dni: String?,
    val idCentro: Int?,
    val nombre: String?,
    val apellidos: String?,
    val email: String?,
    val departamento: String?,
    val rol: String?,
    val statusCode: Int?

)
