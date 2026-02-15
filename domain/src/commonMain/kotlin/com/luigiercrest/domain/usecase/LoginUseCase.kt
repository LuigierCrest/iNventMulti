package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.LoginResponseModel
import com.luigiercrest.domain.repository.LoginRepository

class LoginUseCase (private val loginRepository: LoginRepository) {
    suspend fun login(dni: String, password: String): Result<LoginResponseModel> {
        return loginRepository.login(dni, password)
    }
}