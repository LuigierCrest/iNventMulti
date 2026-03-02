package com.luigiercrest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordResponseDTO(
    val body: String? = null,
    val statusCode: Int? = null
)
