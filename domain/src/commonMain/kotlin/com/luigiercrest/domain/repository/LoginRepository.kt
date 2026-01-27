package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.LoginResponseModel

interface LoginRepository {
    suspend fun login (dni: String, password: String): Result<LoginResponseModel>
}