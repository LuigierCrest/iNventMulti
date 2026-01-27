package com.luigiercrest.domain.Utils

import com.luigiercrest.domain.models.LoginResponseModel
import com.luigiercrest.domain.repository.LoginRepository

class LoginUtil (private val loginRepository: LoginRepository) {
    suspend fun login(dni: String, password: String): Result<LoginResponseModel> {
        return loginRepository.login(dni, password)
    }
}