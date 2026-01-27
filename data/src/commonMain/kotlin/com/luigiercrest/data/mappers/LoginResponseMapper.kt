package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.LoginResponseDTO
import com.luigiercrest.domain.models.LoginResponseModel

object LoginResponseMapper {
    fun toDomain(loginResponseDTO: LoginResponseDTO): LoginResponseModel {
        return LoginResponseModel(
            token = loginResponseDTO.token,
            expiresIn = loginResponseDTO.expiresIn,
            rol = loginResponseDTO.rol,
            idCentro = loginResponseDTO.idCentro,
            idUsuario = loginResponseDTO.idUsuario
        )
    }

}