package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel

interface CategoryRepository {
    suspend fun getCentros(token: String): Result<List<CentroResponseModel>>
    suspend fun getProveedores(token: String): Result<List<ProveedorResponseModel>>
    suspend fun getServicios(token: String): Result<List<ServicioTecnicoResponseModel>>
    suspend fun getAsignaciones(token: String): Result<List<AsignacionResponseModel>>
    suspend fun getUsuarios(token: String): Result<List<UsuarioResponseModel>>
    suspend fun getDispositivos(token: String): Result<List<DispositivoResponseModel>>
    suspend fun getIncidencias(token: String): Result<List<IncidenciaResponseModel>>
    suspend fun getUsuariosCentro(token: String, idCentro: Int): Result<List<UsuarioResponseModel>>
    suspend fun getDispositivosCentro(token: String, idCentro: Int): Result<List<DispositivoResponseModel>>
    suspend fun getIncidenciasCentro(token: String, idCentro: Int): Result<List<IncidenciaResponseModel>>
}