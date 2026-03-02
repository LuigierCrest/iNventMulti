package com.luigiercrest.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.usecase.CreateUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateViewModel(
    private val createUseCase: CreateUseCase,
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _state = MutableStateFlow(CreateUIState())
    val state: StateFlow<CreateUIState> = _state

    fun createUsuario(
        dni: String,
        idCentro: Int,
        nombre: String,
        apellidos: String,
        email: String,
        departamento: String,
        rol: String,
        contrasena: String
    ) {
        viewModelScope.launch {
            _state.value = CreateUIState(isLoading = true)
            val token = secureStorage.getAuth()?.token
            if (token == null) {
                _state.value = CreateUIState(errorMessage = "Token no encontrado")
                return@launch
            }
            val result = createUseCase.createUsuario(
                token = token,
                usuarioModel = UsuarioModel(
                    idUsuario = null,
                    dni = dni,
                    idCentro = idCentro,
                    nombre = nombre,
                    apellidos = apellidos,
                    email = email,
                    departamento = departamento,
                    rol = rol,
                    passwdHash = contrasena
                )
            )
            _state.value = if (result.isSuccess) {
                val response = result.getOrNull()
                CreateUIState(
                    isSuccess = true,
                    serverMessage = response?.body ?: "Usuario creado correctamente"
                )
            } else {
                CreateUIState(errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    fun createIncidencia(
        idCentro: Int,
        idDispositivo: Int,
        idServicioTecnico: Int,
        dniResponsable: String,
        descripcion: String,
        fechaReporte: String,
        estado: String
    ) {
        viewModelScope.launch {
            _state.value = CreateUIState(isLoading = true)
            val token = secureStorage.getAuth()?.token
            if (token == null) {
                _state.value = CreateUIState(errorMessage = "Token no encontrado")
                return@launch
            }
            val result = createUseCase.createIncidencia(
                token = token,
                incidenciaModel = IncidenciaModel(
                    idIncidencia = null,
                    idCentro = idCentro,
                    idDispositivo = idDispositivo,
                    idServicioTecnico = idServicioTecnico,
                    dniResponsable = dniResponsable,
                    descripcion = descripcion,
                    fechaReporte = fechaReporte,
                    fechaCierre = null,
                    estado = estado
                )
            )
            _state.value = if (result.isSuccess) {
                val response = result.getOrNull()
                CreateUIState(
                    isSuccess = true,
                    serverMessage = response?.body ?: "Incidencia creada correctamente"
                )
            } else {
                CreateUIState(errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
}