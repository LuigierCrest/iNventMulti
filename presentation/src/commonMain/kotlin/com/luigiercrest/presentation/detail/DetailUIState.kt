package com.luigiercrest.presentation.detail

import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel

data class DetailUIState (
    val centro: CentroResponseModel? = null,
    val proveedor: ProveedorResponseModel? = null,
    val servicio: ServicioTecnicoResponseModel? = null,
    val asignacion: AsignacionResponseModel? = null,
    val usuario: UsuarioResponseModel? = null,
    val dispositivo: DispositivoResponseModel? = null,
    val incidencia: IncidenciaResponseModel? = null,
    val selectedItem: Any? = null
) {

}