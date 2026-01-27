package com.luigiercrest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val token: String? = null,
    val expiresIn: Int? = null,
    val rol: String? = null,
    val idCentro: Int? = null,
    val idUsuario: Int? = null
)


