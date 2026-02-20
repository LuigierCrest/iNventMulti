package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.ServicioTecnicoDTO
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel

object ServiciosResponseMapper {
    fun map(servicios: List<ServicioTecnicoDTO>, status: Int): List<ServicioTecnicoResponseModel> {
        return servicios.map { servicioDTO ->
            ServicioTecnicoResponseModel(
                idServicioTecnico = servicioDTO.idServicioTecnico,
                nombre = servicioDTO.nombre,
                direccion = servicioDTO.direccion,
                telefono = servicioDTO.telefono,
                email = servicioDTO.email,
                statusCode = status
            )
        }
    }
}