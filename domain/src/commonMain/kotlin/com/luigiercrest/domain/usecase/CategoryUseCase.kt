package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.domain.repository.CategoryRepository

class CategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend fun getCentros(token: String): Result<List<CentroResponseModel>> {
        return categoryRepository.getCentros(token)
    }

    suspend fun getProveedores(token: String): Result<List<ProveedorResponseModel>> {
        return categoryRepository.getProveedores(token)
    }

    suspend fun getServicios(token: String): Result<List<ServicioTecnicoResponseModel>> {
        return categoryRepository.getServicios(token)
    }

    suspend fun getAsignaciones(token: String): Result<List<AsignacionResponseModel>> {
        return categoryRepository.getAsignaciones(token)
    }

    suspend fun getUsuarios(token: String): Result<List<UsuarioResponseModel>> {
        return categoryRepository.getUsuarios(token)
    }

    suspend fun getDispositivos(token: String): Result<List<DispositivoResponseModel>> {
        return categoryRepository.getDispositivos(token)
    }

    suspend fun getIncidencias(token: String): Result<List<IncidenciaResponseModel>> {
        return categoryRepository.getIncidencias(token)
    }

    suspend fun getUsuariosCentro(
        token: String,
        idCentro: Int
    ): Result<List<UsuarioResponseModel>> {
        return categoryRepository.getUsuariosCentro(token, idCentro)
    }

    suspend fun getDispositivosCentro(
        token: String,
        idCentro: Int
    ): Result<List<DispositivoResponseModel>> {
        return categoryRepository.getDispositivosCentro(token, idCentro)
    }

    suspend fun getIncidenciasCentro(
        token: String,
        idCentro: Int
    ): Result<List<IncidenciaResponseModel>> {
        return categoryRepository.getIncidenciasCentro(token, idCentro)
    }
}