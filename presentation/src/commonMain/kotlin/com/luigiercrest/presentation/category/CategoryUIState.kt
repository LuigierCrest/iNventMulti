package com.luigiercrest.presentation.category

import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel


data class CategoryUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val centros: List<CentroResponseModel> = emptyList(),
    val proveedores: List<ProveedorResponseModel> = emptyList(),
    val servicios: List<ServicioTecnicoResponseModel> = emptyList(),
    val asignaciones: List<AsignacionResponseModel> = emptyList(),
    val usuarios: List<UsuarioResponseModel> = emptyList(),
    val dispositivos: List<DispositivoResponseModel> = emptyList(),
    val incidencias: List<IncidenciaResponseModel> = emptyList()
)
