package com.luigiercrest.presentation.detail

sealed class UpdateState {
    object Idle : UpdateState()
    object Loading : UpdateState()
    data class Success(val message: String = "Actualizado correctamente") : UpdateState()
    data class Error(val message: String) : UpdateState()

}