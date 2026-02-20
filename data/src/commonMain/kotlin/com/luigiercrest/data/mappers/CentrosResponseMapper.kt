package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.CentroDTO
import com.luigiercrest.domain.models.CentroResponseModel

object CentrosResponseMapper {
    fun map(centros: List<CentroDTO>, status: Int): List<CentroResponseModel> {
        return centros.map { centroDTO ->
            CentroResponseModel(
                idCentro = centroDTO.idCentro,
                tipo = centroDTO.tipo,
                nombre = centroDTO.nombre,
                direccion = centroDTO.direccion,
                municipio = centroDTO.municipio,
                statusCode = status
            )
        }
    }
}