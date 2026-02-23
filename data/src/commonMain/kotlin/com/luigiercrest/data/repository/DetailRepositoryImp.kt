package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.datasource.NetworkResult
import com.luigiercrest.data.dto.DeleteResponseDTO
import com.luigiercrest.data.mappers.DeleteResponseMapper
import com.luigiercrest.domain.models.DeleteResponseModel
import com.luigiercrest.domain.repository.DetailRepository

class DetailRepositoryImp (private val apiConnection: ApiConnection) : DetailRepository {
    // Actualizar, borrar y crear nuevos registros
    override suspend fun deleteDispositivo(
        token: String,
        idDispositivo: Int
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteDispositivo(token, idDispositivo)
            if (netResult.isFailure) {
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!

            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteDispositivo 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el dispositivo porque tiene incidencia abierta)")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando dispositivo: HTTP ${network.status}"))
                }
            }

        }catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun deleteIncidencia(
        token: String,
        idIncidencia: Int
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteIncidencia(token, idIncidencia)
            if (netResult.isFailure) {
                println("LOG- Error en deleteIncidencia en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            if (network.body != null) {
                val model = DeleteResponseMapper.toDomain(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.rawBody} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en deleteIncidencia IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteUsuario(
        token: String,
        dniUsuario: String
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteUsuario(token, dniUsuario)
            if (netResult.isFailure) {
                println("LOG- Error en deleteUsuario en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteUsuario 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el usuario porque tiene incidencias asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando usuario: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception) {
            println("LOG- Error en deleteUsuario IMP: ${e.message}")
            Result.failure(e)
        }
    }


}