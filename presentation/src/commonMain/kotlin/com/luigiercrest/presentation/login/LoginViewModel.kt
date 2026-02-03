package com.luigiercrest.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.Utils.LoginUtil
import com.luigiercrest.presentation.security.AuthData
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUtil: LoginUtil, private val secureStorage: SecureStorage) : ViewModel() {
    private val _navigationState = MutableStateFlow<AuthNavigation?>(null)
    val navigationState = _navigationState.asSharedFlow()

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
                // Realiza la llamada a la API utilizando el LoginUtil
                val result = loginUtil.login(dni.value, password.value)

                result.onSuccess { loginResponseModel ->
                    // Actualiza el estado con los datos del login
                    _state.value = _state.value.copy(login = loginResponseModel, isLoading = false)
                    // Almacena los datos de autenticación en SecureStorage
                    val token = loginResponseModel.token
                    val rol = loginResponseModel.rol
                    val expiresIn = loginResponseModel.expiresIn
                    val idCentro = loginResponseModel.idCentro
                    val idUsuario = loginResponseModel.idUsuario
                    if (!token.isNullOrEmpty()) {
                        secureStorage.saveAuth(
                            AuthData(
                                token = token,
                                rol = rol,
                                expiresIn = expiresIn.toString(),
                                idCentro = idCentro,
                                idUsuario = idUsuario
                            )
                        )
                    }
                    // Navega a la HomeScreen
                     _navigationState.emit(AuthNavigation.ToHome)

                }.onFailure { error ->
                    println("LOG- Error en login: ${error.message}")
                    error.printStackTrace()
                    _state.value = _state.value.copy(error = error.message, isLoading = false)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = _state.value.copy(
                    error = "Excepción: ${e.message}",
                    isLoading = false)
            }




        }
    }
}