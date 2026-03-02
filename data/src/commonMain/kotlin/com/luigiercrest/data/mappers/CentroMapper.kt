package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.CentroDTO
import com.luigiercrest.domain.models.CentroModel

object CentroMapper {
    fun toDTO(centroModel: CentroModel): CentroDTO{
        return CentroDTO(
            idCentro = centroModel.idCentro ?: 0,
            tipo = centroModel.tipo ?: "",
            nombre = centroModel.nombre ?: "",
            direccion = centroModel.direccion,
            municipio = centroModel.municipio
        )
    }
}