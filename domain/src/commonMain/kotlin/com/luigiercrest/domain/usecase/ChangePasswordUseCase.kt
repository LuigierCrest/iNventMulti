package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.ChangePasswordResponseModel
import com.luigiercrest.domain.repository.ChangePasswordRepository

data class ChangePasswordUseCase(private val changePasswordRepository: ChangePasswordRepository) {
    suspend fun changePassword(token: String, newPassword: String, idUsuario: Int): Result<ChangePasswordResponseModel> {
        return changePasswordRepository.changePassword(token, newPassword, idUsuario)
    }
}
