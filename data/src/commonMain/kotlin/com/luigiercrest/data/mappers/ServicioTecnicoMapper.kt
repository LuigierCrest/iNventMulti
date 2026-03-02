package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.ServicioTecnicoDTO
import com.luigiercrest.domain.models.ServicioTecnicoModel

object ServicioTecnicoMapper {
    fun toDTO (servicioTecnicoModel: ServicioTecnicoModel): ServicioTecnicoDTO{
        return ServicioTecnicoDTO(
            idServicioTecnico = servicioTecnicoModel.idServicioTecnico ?: 0,
            nombre = servicioTecnicoModel.nombre ?: "",
            direccion = servicioTecnicoModel.direccion ?: "",
            telefono = servicioTecnicoModel.telefono ?: "",
            email = servicioTecnicoModel.email ?: ""
        )
    }
}