package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.ChangePasswordResponseDTO
import com.luigiercrest.domain.models.ChangePasswordResponseModel

object ChangePasswordResponseMapper {
    fun toDomain(dto: ChangePasswordResponseDTO, status: Int): ChangePasswordResponseModel {
        return ChangePasswordResponseModel(
            body = dto.body,
            statusCode = status
        )
    }
}