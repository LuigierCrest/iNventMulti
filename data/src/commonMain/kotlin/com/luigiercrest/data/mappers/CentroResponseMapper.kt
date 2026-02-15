package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.CentroDTO
import com.luigiercrest.domain.models.CentroResponseModel

object CentroResponseMapper {
    fun toDomain(centroDTO: CentroDTO, status: Int): CentroResponseModel {
        return CentroResponseModel(
            idCentro = centroDTO.idCentro,
            tipo = centroDTO.tipo,
            nombre = centroDTO.nombre,
            direccion = centroDTO.direccion,
            municipio = centroDTO.municipio,
            statusCode = status
        )

    }
}