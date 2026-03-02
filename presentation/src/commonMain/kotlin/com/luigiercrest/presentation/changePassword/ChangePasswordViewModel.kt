package com.luigiercrest.presentation.changePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.usecase.ChangePasswordUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _state = MutableStateFlow(ChangePasswordUIState())
    val state = _state.asStateFlow()

    private var _token: String = ""
    private var _idUsuario: Int = 0

    init {
        viewModelScope.launch {
            val authData = secureStorage.getAuth()
            _token = authData?.token ?: ""
            _idUsuario = authData?.idUsuario ?: 0
        }
    }

    fun changePassword(newPassword: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, successMessage = null)
            val result = changePasswordUseCase.changePassword(_token, newPassword, _idUsuario)
            result.onSuccess {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    successMessage = "Contraseña cambiada correctamente"
                )
                println("LOG - VM Contraseña cambiada exitosamente")
            }.onFailure {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = it.message,
                    successMessage = null
                )
                println("LOG - VM Error al cambiar la contraseña: ${it.message}")
            }
        }
    }

}