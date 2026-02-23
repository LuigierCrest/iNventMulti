package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.DeleteResponseDTO
import com.luigiercrest.domain.models.DeleteResponseModel

object DeleteResponseMapper {
    fun toDomain(deleteResponseDTO: DeleteResponseDTO, status: Int): DeleteResponseModel {
        return DeleteResponseModel(
            body = deleteResponseDTO.body,
            statusCode = status
        )
    }
}