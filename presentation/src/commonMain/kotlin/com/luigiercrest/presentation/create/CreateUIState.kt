package com.luigiercrest.presentation.create

data class CreateUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val serverMessage: String? = null
)
