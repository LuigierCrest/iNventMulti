package com.luigiercrest.data.repository

import com.luigiercrest.data.mappers.LoginResponseMapper
import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.dto.LoginDataDTO
import com.luigiercrest.domain.repository.LoginRepository
import com.luigiercrest.domain.models.LoginResponseModel


class LoginRepositoryImp(private val connection: ApiConnection) : LoginRepository {
    override suspend fun login(
        dni: String,
        password: String
    ): Result<LoginResponseModel> {
        return try {
            val netResult = connection.login(LoginDataDTO(dni, password))
            if (netResult.isFailure) {
                return Result.failure(netResult.exceptionOrNull()!!)
            }

            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                // Respuesta parseada, envia statusCode al modelo
                val model = LoginResponseMapper.toDomain(network.body, network.status)
                Result.success(model)
            } else {
                // No hay DTO (por ejemplo 401 con text/plain). Incluir el body y el código en el error.
                val msg = "HTTP ${network.status}: ${network.rawBody ?: "Sin cuerpo"}"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}