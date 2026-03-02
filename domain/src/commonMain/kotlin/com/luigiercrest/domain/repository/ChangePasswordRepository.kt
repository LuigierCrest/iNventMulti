package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.ChangePasswordResponseModel

interface ChangePasswordRepository {
    suspend fun changePassword(token: String, newPassword: String, idUsuario: Int): Result<ChangePasswordResponseModel>
}