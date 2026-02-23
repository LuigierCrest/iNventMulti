package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.DeleteResponseModel

interface DetailRepository {
    suspend fun deleteDispositivo(token: String, idDispositivo: Int): Result<DeleteResponseModel>
    suspend fun deleteIncidencia(token: String, idIncidencia: Int): Result<DeleteResponseModel>
    suspend fun deleteUsuario(token: String, dniUsuario: String): Result<DeleteResponseModel>
}

