package com.luigiercrest.presentation.login

import com.luigiercrest.presentation.security.SecureStorage

sealed class AuthNavigation {
    object ToLogin : AuthNavigation()
    object ToHome : AuthNavigation()
}