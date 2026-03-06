package com.luigiercrest.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.usecase.CategoryUseCase
import com.luigiercrest.domain.usecase.CreateUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateViewModel(
    private val createUseCase: CreateUseCase,
    private val secureStorage: SecureStorage,
    private val categoryUseCase: CategoryUseCase
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
        password: String
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
                    passwdHash = password
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

    fun resetState() {
        _state.value = CreateUIState()
    }

    fun loadServicios() {
        viewModelScope.launch {
            val token = secureStorage.getAuth()?.token ?: return@launch
            val result = categoryUseCase.getServicios(token)
            if (result.isSuccess) {
                _state.value = _state.value.copy(serviciosTecnicos = result.getOrDefault(emptyList()))
            }
        }
    }

    fun createIncidencia(
        idCentro: Int,
        idDispositivo: Int,
        idServicioTecnico: Int,
        dniResponsable: String,
        descripcion: String,
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

    suspend fun getCurrentUserDni(): String {
        val idUsuario = secureStorage.getAuth()?.idUsuario
        val token = secureStorage.getAuth()?.token
        val result = createUseCase.getUsuarioById(token ?: "", idUsuario ?: 0)
        return if (result.isSuccess) {
            result.getOrNull()?.dni ?: "DNI no encontrado"
        } else {
            "Error al obtener DNI"
        }
    }
}