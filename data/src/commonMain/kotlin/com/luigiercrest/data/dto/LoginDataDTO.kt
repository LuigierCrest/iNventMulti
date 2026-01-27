package com.luigiercrest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDataDTO(
    val dni: String,
    val passwd: String
)