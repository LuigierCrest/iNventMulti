package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.mappers.IncidenciaMapper
import com.luigiercrest.data.mappers.UsuarioMapper
import com.luigiercrest.domain.models.CreateResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.repository.CreateRepository

class CreateRepositoryImp(
    private val apiConnection: ApiConnection
) : CreateRepository {

    override suspend fun createUsuario(token: String, usuarioModel: UsuarioModel): Result<CreateResponseModel> {
        return try {
            val dto = UsuarioMapper.toDTO(usuarioModel)
            val netResult = apiConnection.createUsuario(token, dto)
            if (netResult.isFailure) {
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            Result.success(
                CreateResponseModel(
                    body = network.body?.body,
                    statusCode = network.status
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createIncidencia(token: String, incidenciaModel: IncidenciaModel): Result<CreateResponseModel> {
        return try {
            val dto = IncidenciaMapper.toDTO(incidenciaModel)
            val netResult = apiConnection.createIncidencia(token, dto)
            if (netResult.isFailure) {
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            Result.success(
                CreateResponseModel(
                    body = network.body?.body,
                    statusCode = network.status
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}