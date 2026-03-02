package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.AsignacionDTO
import com.luigiercrest.domain.models.AsignacionModel

object AsignacionMapper {
    fun toDTO(asignacionModel: AsignacionModel): AsignacionDTO {
        return AsignacionDTO(
            idAsignacionCompra = asignacionModel.idAsignacion ?: 0,
            idCentro = asignacionModel.idCentro ?: 0,
            idProveedor = asignacionModel.idProveedor ?: 0,
            entrega = asignacionModel.entrega ?: "",
        )
    }
}