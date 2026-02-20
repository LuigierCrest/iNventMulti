package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.AsignacionDTO
import com.luigiercrest.domain.models.AsignacionResponseModel

object AsignacionesResponseMapper {
    fun map(asignaciones: List<AsignacionDTO>, status: Int): List<AsignacionResponseModel> {
        return asignaciones.map { asignacionDTO ->
            AsignacionResponseModel(
                idAsignacion = asignacionDTO.idAsignacionCompra,
                idCentro = asignacionDTO.idCentro,
                idProveedor = asignacionDTO.idProveedor,
                entrega = asignacionDTO.entrega,
                statusCode = status
            )
        }
    }
}
