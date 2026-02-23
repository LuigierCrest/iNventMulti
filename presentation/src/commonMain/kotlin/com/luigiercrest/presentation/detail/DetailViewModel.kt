package com.luigiercrest.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.domain.usecase.DetailUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DeleteState {
    object Idle : DeleteState()
    object Loading : DeleteState()
    object Success : DeleteState()
    data class Error(val message: String) : DeleteState()
}

class DetailViewModel(
    private val detailUseCase: DetailUseCase,
    private val secureStorage: SecureStorage
) : ViewModel() {
    private val _state = MutableStateFlow(DetailUIState())
    val state = _state.asStateFlow()

//    fun loadDetails(itemId: Int, categoryId: Int) {    }

    fun setCentro(centro: CentroResponseModel) {
        _state.value = _state.value.copy(centro = centro)
    }

    fun setProveedor(proveedor: ProveedorResponseModel) {
        _state.value = _state.value.copy(proveedor = proveedor)
    }

    fun setServicio(servicio: ServicioTecnicoResponseModel) {
        _state.value = _state.value.copy(servicio = servicio)
    }

    fun setAsignacion(asignacion: AsignacionResponseModel) {
        _state.value = _state.value.copy(asignacion = asignacion)
    }

    fun setUsuario(usuario: UsuarioResponseModel) {
        _state.value = _state.value.copy(usuario = usuario)
    }

    fun setDispositivo(dispositivo: DispositivoResponseModel) {
        _state.value = _state.value.copy(dispositivo = dispositivo)
    }

    fun setIncidencia(incidencia: IncidenciaResponseModel) {
        _state.value = _state.value.copy(incidencia = incidencia)
    }

    fun setSelectedItem(item: Any?) {
        _state.value = _state.value.copy(selectedItem = item)
        when (item) {
            is CentroResponseModel -> setCentro(item)
            is ProveedorResponseModel -> setProveedor(item)
            is ServicioTecnicoResponseModel -> setServicio(item)
            is AsignacionResponseModel -> setAsignacion(item)
            is UsuarioResponseModel -> setUsuario(item)
            is DispositivoResponseModel -> setDispositivo(item)
            is IncidenciaResponseModel -> setIncidencia(item)
        }
    }
    private val _deleteState = MutableStateFlow<DeleteState>(DeleteState.Idle)
    val deleteState = _deleteState.asStateFlow()

    fun deleteDispositivo(idDispositivo: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteDispositivo(token, idDispositivo)
                    .onSuccess {
                        println("LOG- deleteDispositivo Success: ${it.body}")
                        _deleteState.value = DeleteState.Success
                    }
                    .onFailure {
                        println("LOG- deleteDispositivo Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteDispositivo Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun deleteIncidencia(idIncidencia: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteIncidencia(token, idIncidencia)
                    .onSuccess {
                        println("LOG- deleteIncidencia Success: ${it.body}")
                        _deleteState.value = DeleteState.Success
                    }
                    .onFailure {
                        println("LOG- deleteIncidencia Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteIncidencia Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun deleteUsuario(dniUsuario: String) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteUsuario(token, dniUsuario)
                    .onSuccess {
                        println("LOG- deleteUsuario Success: ${it.body}")
                        _deleteState.value = DeleteState.Success
                    }
                    .onFailure {
                        println("LOG- deleteUsuario Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteUsuario Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }fun resetDeleteState() {
        _deleteState.value = DeleteState.Idle
    }



}