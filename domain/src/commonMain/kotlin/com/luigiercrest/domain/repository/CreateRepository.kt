package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.CreateResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel

interface CreateRepository {
    suspend fun createUsuario(
        token: String,
        usuarioModel: UsuarioModel
    ): Result<CreateResponseModel>

        suspend fun createIncidencia(
            token: String,
            incidenciaModel: IncidenciaModel
        ): Result<CreateResponseModel>
}