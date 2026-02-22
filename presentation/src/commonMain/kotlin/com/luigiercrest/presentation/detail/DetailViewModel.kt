package com.luigiercrest.presentation.detail

import androidx.lifecycle.ViewModel
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

}