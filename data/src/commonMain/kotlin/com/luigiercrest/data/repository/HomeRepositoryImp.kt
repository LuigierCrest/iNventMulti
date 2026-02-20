package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.mappers.CentroResponseMapper
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.repository.HomeRepository

class HomeRepositoryImp (private val connection: ApiConnection) : HomeRepository {
    override suspend fun getCentroHome(
        idCentro: String,
        token: String
    ): Result<CentroResponseModel> {
        return try {
            val netResult = connection.getCentroHome(idCentro, token)
            if (netResult.isFailure) {
                println("LOG- Error en getCentroHome en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = CentroResponseMapper.toDomain(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.rawBody} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getCentroHome IMP: ${e.message}")
            Result.failure(e)
        }
    }
}

