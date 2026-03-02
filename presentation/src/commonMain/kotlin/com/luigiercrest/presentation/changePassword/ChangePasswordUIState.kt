package com.luigiercrest.presentation.changePassword
data class ChangePasswordUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)
