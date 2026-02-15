package com.luigiercrest.presentation.navigation

sealed class AuthNavigation {
    //object ToLogin : AuthNavigation()
    object ToHome : AuthNavigation()
}