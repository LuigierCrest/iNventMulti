package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.mappers.ChangePasswordResponseMapper
import com.luigiercrest.domain.models.ChangePasswordResponseModel
import com.luigiercrest.domain.repository.ChangePasswordRepository

class ChangePasswordRepositoryImp(private val connection: ApiConnection) : ChangePasswordRepository {
    override suspend fun changePassword(
        token: String,
        newPassword: String,
        idUsuario: Int
    ): Result<ChangePasswordResponseModel> {
        return try {
            val netResult = connection.changePassword(token, newPassword, idUsuario)
            if (netResult.isFailure) {
                println("LOG - Error en changePassword llamada API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.status in 200..299) {
                val dto = network.body!!
                val model = ChangePasswordResponseMapper.toDomain(dto, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.rawBody}"
                println("LOG - changePassword Error HTTP: $msg")
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG - Error en changePassword IMP: ${e.message}")
            Result.failure(e)
        }
    }
}
