package com.luigiercrest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteResponseDTO(
    val body: String? = null,
    val statusCode: Int? = null
)
