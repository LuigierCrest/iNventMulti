package com.luigiercrest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.usecase.HomeUseCase
import com.luigiercrest.presentation.navigation.AuthNavigation
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase, private val secureStorage: SecureStorage): ViewModel() {
    private val _navigationState = MutableStateFlow<AuthNavigation?>(null)
    val navigationState = _navigationState.asSharedFlow()

    // uistate
    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()


    // Datos de autenticación
    private val _token = MutableStateFlow("")
    private val _rol = MutableStateFlow("")
    private val _expiresIn = MutableStateFlow("")
    private val _idCentro = MutableStateFlow("")
    private val _idUsuario = MutableStateFlow("")
    private val _centro = MutableStateFlow("")

    val rol = _rol.asStateFlow()
    val centro: StateFlow<String> = _centro.asStateFlow()
    val expiresIn = _expiresIn.asStateFlow()

    init {
        viewModelScope.launch {
            val authData = secureStorage.getAuth()
            _rol.value = authData?.rol ?: ""
            _token.value = authData?.token ?: ""
            _idCentro.value = authData?.idCentro.toString()
//            _idUsuario.value = authData?.idUsuario.toString()

            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val result= homeUseCase.getCentroHome(_idCentro.value, _token.value)
                result.onSuccess { centroResponseModel ->
                    println("LOG - getCentroHome Succces: ${centroResponseModel.nombre}")
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = null,
                        centro = centroResponseModel
                    )
                }.onFailure {
                    println("LOG - Error en getCentroHome: ${it.message}")
                    _state.value = _state.value.copy(isLoading = false, error = it.message)
                }

            } catch (e: Exception) {
                println("LOG - Error en getCentroHome: ${e.message}")
            }

        }

    }


}