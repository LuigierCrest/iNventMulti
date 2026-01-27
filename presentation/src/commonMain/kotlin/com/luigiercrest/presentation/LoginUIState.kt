package com.luigiercrest.presentation

import com.luigiercrest.domain.models.LoginResponseModel

data class LoginUIState (
    val login: LoginResponseModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {

}