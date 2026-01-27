package com.luigiercrest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.Utils.LoginUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUtil: LoginUtil) : ViewModel() {
    private val _state = MutableStateFlow(LoginUIState())
    val state = _state.asStateFlow()

    private val _dni = MutableStateFlow("")
    val dni = _dni.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()


    fun onDniChanged(dni: String) {
        _dni.value = dni
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun comenzar() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                println("Intentando conectar a la API...")
                println("DNI: ${dni.value}")

                val result = loginUtil.login(dni.value, password.value)

                result.onSuccess { loginResponseModel ->
                    println("Login exitoso: $loginResponseModel")
                    _state.value = _state.value.copy(login = loginResponseModel, isLoading = false)
                }.onFailure { error ->
                    println("Error en login: ${error.message}")
                    error.printStackTrace()
                    _state.value = _state.value.copy(error = error.message, isLoading = false)
                }

            } catch (e: Exception) {
                println("Excepción en ViewModel: ${e.message}")
                e.printStackTrace()
                _state.value = _state.value.copy(
                    error = "Excepción: ${e.message}",
                    isLoading = false)
            }




        }
    }
}
