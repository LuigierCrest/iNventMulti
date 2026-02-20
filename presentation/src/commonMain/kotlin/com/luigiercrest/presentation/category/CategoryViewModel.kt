package com.luigiercrest.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.usecase.CategoryUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.onFailure

class CategoryViewModel(
    private val categoryUseCase: CategoryUseCase,
    private val secureStorage: SecureStorage
) : ViewModel() {

    // necesito el SecureStorage para el token y poder hacer las llamadas
    // Dependiendo de la categoría que se ha elegido se deberá llamar a la función adecuada para recoger la lista de items de las diferentes categorías
    private val _state = MutableStateFlow(CategoryUIState())
    val state = _state.asStateFlow()

    // Recoge ID de la categoría seleccionada
    private val _categoryId = MutableStateFlow<Int?>(null)
    fun setCategoryId(categoryId: Int) {
        _categoryId.value = categoryId
        loadCategory(_categoryId)
    }

    fun loadCategory(categoryId: StateFlow<Int?>) {

        // Recoge el token y el ID del centro del usuario
        viewModelScope.launch {
            val authData = secureStorage.getAuth()
            val token = authData?.token ?: ""
            val idCentro = authData?.idCentro ?: 0


            when (categoryId.value) {
                1 -> loadCentros(token)
                2 -> loadProveedores(token)
                3 -> loadServicios(token)
                4 -> loadAsignaciones(token)
                5 -> loadUsuarios(token)
                6 -> loadDispositivos(token)
                7 -> loadIncidencias(token)
                8 -> loadUsuariosCentro(token, idCentro)
                9 -> loadDispositivosCentro(token, idCentro)
                10 -> loadIncidenciasCentro(token, idCentro)
            }
        }
    }

    private suspend fun loadCentros(token: String) {
        try {
            val result = categoryUseCase.getCentros(token)
            result.onSuccess { centrosResponseModel ->
                println("LOG- getCentros Succces: ${centrosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    centros = centrosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getCentros: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getCentros: ${e.message}")
        }
    }

    private suspend fun loadProveedores(token: String) {
        try {
            val result = categoryUseCase.getProveedores(token)
            result.onSuccess { proveedoresResponseModel ->
                println("LOG- getProveedores Succces: ${proveedoresResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    proveedores = proveedoresResponseModel
                )
            }.onFailure {
                println("LOG- Error en getProveedores: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getProveedores: ${e.message}")
        }
    }

    private suspend fun loadServicios(token: String) {
        try {
            val result = categoryUseCase.getServicios(token)
            result.onSuccess { serviciosResponseModel ->
                println("LOG- getServicios Succces: ${serviciosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    servicios = serviciosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getServicios: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getServicios: ${e.message}")
        }
    }

    private suspend fun loadAsignaciones(token: String) {
        try {
            val result = categoryUseCase.getAsignaciones(token)
            result.onSuccess { asignacionesResponseModel ->
                println("LOG- getAsignaciones Succces: ${asignacionesResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    asignaciones = asignacionesResponseModel
                )
            }.onFailure {
                println("LOG- Error en getAsignaciones: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getAsignaciones: ${e.message}")
        }
    }

    private suspend fun loadUsuarios(token: String) {
        try {
            val result = categoryUseCase.getUsuarios(token)
            result.onSuccess { usuariosResponseModel ->
                println("LOG- getUsuarios Succces: ${usuariosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    usuarios = usuariosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getUsuarios: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getUsuarios: ${e.message}")
        }
    }

    private suspend fun loadDispositivos(token: String) {
        try {
            val result = categoryUseCase.getDispositivos(token)
            result.onSuccess { dispositivosResponseModel ->
                println("LOG- getDispositivos Succces: ${dispositivosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    dispositivos = dispositivosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getDispositivos: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getDispositivos: ${e.message}")
        }
    }

    private suspend fun loadIncidencias(token: String) {
        try {
            val result = categoryUseCase.getIncidencias(token)
            result.onSuccess { incidenciasResponseModel ->
                println("LOG- getIncidencias Succces: ${incidenciasResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    incidencias = incidenciasResponseModel
                )
            }.onFailure {
                println("LOG- Error en getIncidencias: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getIncidencias: ${e.message}")
        }
    }

    private suspend fun loadUsuariosCentro(token: String, idCentro: Int) {
        try {
            val result = categoryUseCase.getUsuariosCentro(token, idCentro)
            result.onSuccess { usuariosResponseModel ->
                println("LOG- getUsuariosCentro Succces: ${usuariosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    usuarios = usuariosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getUsuariosCentro: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getUsuariosCentro: ${e.message}")
        }
    }

    private suspend fun loadDispositivosCentro(token: String, idCentro: Int) {
        try {
            val result = categoryUseCase.getDispositivosCentro(token, idCentro)
            result.onSuccess { dispositivosResponseModel ->
                println("LOG- getDispositivosCentro Succces: ${dispositivosResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    dispositivos = dispositivosResponseModel
                )
            }.onFailure {
                println("LOG- Error en getDispositivosCentro: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getDispositivosCentro: ${e.message}")
        }
    }

    private suspend fun loadIncidenciasCentro(token: String, idCentro: Int) {
        try {
            val result = categoryUseCase.getIncidenciasCentro(token, idCentro)
            result.onSuccess { incidenciasResponseModel ->
                println("LOG- getIncidenciasCentro Succces: ${incidenciasResponseModel.size}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    incidencias = incidenciasResponseModel
                )
            }.onFailure {
                println("LOG- Error en getIncidenciasCentro: ${it.message}")
                _state.value = _state.value.copy(isLoading = false, error = it.message)
            }
        } catch (e: Exception) {
            println("LOG- Error en getIncidenciasCentro: ${e.message}")
        }
    }


}