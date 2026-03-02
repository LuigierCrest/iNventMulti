package com.luigiercrest.presentation.detail

sealed class DeleteState {
    object Idle : DeleteState()
    object Loading : DeleteState()
    data class Success(val message: String = "Borrado correctamente") : DeleteState()
    data class Error(val message: String) : DeleteState()
}