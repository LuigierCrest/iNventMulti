package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.CreateResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.repository.CreateRepository

class CreateUseCase (
    private val createRepositoty: CreateRepository
) {
    suspend fun createUsuario(token: String, usuarioModel: UsuarioModel): Result<CreateResponseModel>{
        return createRepositoty.createUsuario(token,usuarioModel)
    }

    suspend fun createIncidencia(token: String, incidenciaModel: IncidenciaModel): Result<CreateResponseModel>{
        return createRepositoty.createIncidencia(token, incidenciaModel)
    }


}