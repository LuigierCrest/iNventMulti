package com.luigiercrest.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigiercrest.domain.models.AsignacionModel
import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.domain.usecase.DetailUseCase
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
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
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
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
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
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
    }

    fun deleteCentro(idCentro: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteCentro(token, idCentro)
                    .onSuccess {
                        println("LOG- deleteCentro Success: ${it.body}")
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
                    }
                    .onFailure {
                        println("LOG- deleteCentro Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteCentro Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun deleteProveedor(idProveedor: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteProveedor(token, idProveedor)
                    .onSuccess {
                        println("LOG- deleteProveedor Success: ${it.body}")
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
                    }
                    .onFailure {
                        println("LOG- deleteProveedor Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteProveedor Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun deleteServicioTecnico(idServicio: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteServicioTecnico(token, idServicio)
                    .onSuccess {
                        println("LOG- deleteServicio Success: ${it.body}")
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
                    }
                    .onFailure {
                        println("LOG- deleteServicio Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteServicio Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun deleteAsignacion(idAsignacion: Int) {
        viewModelScope.launch {
            _deleteState.value = DeleteState.Loading
            try {
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""

                detailUseCase.deleteAsignacion(token, idAsignacion)
                    .onSuccess {
                        println("LOG- deleteAsignacion Success: ${it.body}")
                        _deleteState.value = DeleteState.Success(it.body ?: "Borrado correctamente")
                    }
                    .onFailure {
                        println("LOG- deleteAsignacion Error: ${it.message}")
                        _deleteState.value = DeleteState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- deleteAsignacion Exception: ${e.message}")
                _deleteState.value = DeleteState.Error(e.message ?: "Error desconocido")
            }
        }
    }


    fun resetDeleteState() {
        _deleteState.value = DeleteState.Idle
    }

    fun updateCentro(centro: CentroModel){
        viewModelScope.launch {
                _updateState.value = UpdateState.Loading
                try {
                    // Actualiza el centro usando el use case correspondiente
                     val authData = secureStorage.getAuth()
                     val token = authData?.token ?: ""
                     detailUseCase.updateCentro(token, centro)
                         .onSuccess {
                             println("LOG- updateCentro Success: ${it.body}")
                             _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                         }
                         .onFailure {
                             println("LOG- updateCentro Error: ${it.message}")
                             _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                         }
                } catch (e: Exception) {
                    println("LOG- updateCentro Exception: ${e.message}")
                    _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
                }
        }
    }

    fun updateProveedor(proveedor: ProveedorModel){
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza el proveedor usando el id de proveedor y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateProveedor(token, proveedor)
                    .onSuccess {
                        println("LOG- updateProveedor Success: ${it.body}")
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateProveedor Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateProveedor Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun updateServicioTecnico (servicioTecnico : ServicioTecnicoModel) {
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza el servicio técnico usando el id de servicio técnico y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateServicioTecnico(token, servicioTecnico)
                    .onSuccess {
                        println("LOG- updateServicio Success: ${it.body}")
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateServicio Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateServicio Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun updateAsignacion (asignacion: AsignacionModel){
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza la asignación usando el id de asignación y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateAsignacion(token, asignacion)
                    .onSuccess {
                        println("LOG- updateAsignacion Success: ${it.body}")
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateAsignacion Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateAsignacion Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun updateUsuario (usuario: UsuarioModel){
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza el usuario usando el dni de usuario y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateUsuario(token, usuario)
                    .onSuccess {
                        println("LOG- updateUsuario Success: ${it.body}")
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateUsuario Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateUsuario Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun updateDispositivo (dispositivo : DispositivoModel){
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza el dispositivo usando el id de dispositivo y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateDispositivo(token, dispositivo)
                    .onSuccess {
                        println("LOG- updateDispositivo Success: ${it.body}")
                        _state.value.dispositivo?.let { current ->
                            _state.value = _state.value.copy(
                                dispositivo = current.copy(
                                    ultimaActualizacion = dispositivo.ultimaActualizacion
                                )
                            )
                        }
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateDispositivo Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateDispositivo Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun updateIncidencia(incidencia: IncidenciaModel){
        viewModelScope.launch {
            _updateState.value = UpdateState.Loading
            try {
                // Actualiza la incidencia usando el id de incidencia y el use case correspondiente
                val authData = secureStorage.getAuth()
                val token = authData?.token ?: ""
                detailUseCase.updateIncidencia(token, incidencia)
                    .onSuccess {
                        println("LOG- updateIncidencia Success: ${it.body}")
                        _updateState.value = UpdateState.Success(it.body ?: "Actualizado correctamente")
                    }
                    .onFailure {
                        println("LOG- updateIncidencia Error: ${it.message}")
                        _updateState.value = UpdateState.Error(it.message ?: "Error desconocido")
                    }
            } catch (e: Exception) {
                println("LOG- updateIncidencia Exception: ${e.message}")
                _updateState.value = UpdateState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetUpdateState() {
        _updateState.value = UpdateState.Idle
    }

    private val _updateState = MutableStateFlow<UpdateState>(UpdateState.Idle)
    val updateState = _updateState.asStateFlow()

    // Estados editados para cada entidad
    private var _editedProveedor: ProveedorModel? = null
    private var _editedServicioTecnico: ServicioTecnicoModel? = null
    private var _editedDispositivo: DispositivoModel? = null
    // Función genérica para guardar según la entidad editada
    fun saveCurrentEdited() {
        _editedProveedor?.let { updateProveedor(it); return }
        _editedServicioTecnico?.let { updateServicioTecnico(it); return }
        _editedDispositivo?.let { updateDispositivo(it); return }
    }
    fun clearEdited() {
        _editedProveedor = null
        _editedServicioTecnico = null
        _editedDispositivo = null
    }

    fun clearState() {
        _state.value = DetailUIState()
        _deleteState.value = DeleteState.Idle
        _updateState.value = UpdateState.Idle
        clearEdited()
    }





}