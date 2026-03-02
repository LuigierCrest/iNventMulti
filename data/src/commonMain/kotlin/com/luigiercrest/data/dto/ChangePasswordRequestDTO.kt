package com.luigiercrest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequestDTO(
    val newPassword: String
)

