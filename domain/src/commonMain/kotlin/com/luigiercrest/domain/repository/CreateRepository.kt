package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.CreateResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.models.UsuarioResponseModel

interface CreateRepository {
    suspend fun createUsuario(
        token: String,
        usuarioModel: UsuarioModel
    ): Result<CreateResponseModel>

    suspend fun createIncidencia(
        token: String,
        incidenciaModel: IncidenciaModel
    ): Result<CreateResponseModel>

    suspend fun getUsuarioById(
        token: String,
        idUsuario: Int
    ): Result<UsuarioResponseModel>
}