package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.LoginResponseDTO
import com.luigiercrest.domain.models.LoginResponseModel

object LoginResponseMapper {
    fun toDomain(loginResponseDTO: LoginResponseDTO, status: Int): LoginResponseModel {
        return LoginResponseModel(
            token = loginResponseDTO.token,
            expiresIn = loginResponseDTO.expiresIn,
            rol = loginResponseDTO.rol,
            idCentro = loginResponseDTO.idCentro,
            idUsuario = loginResponseDTO.idUsuario,
            apiCodes = loginResponseDTO.apiCodes,
            apiMessages = loginResponseDTO.apiMessages,
            statusCode = status

        )
    }

}