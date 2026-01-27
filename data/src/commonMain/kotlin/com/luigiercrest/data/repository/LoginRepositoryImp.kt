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
        return try{
            val response = connection.login(LoginDataDTO(dni, password))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val loginResponseModel = LoginResponseMapper.toDomain(response
                )
                Result.success(loginResponseModel)
            } else {
                Result.failure(Exception("Falló el login : ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}