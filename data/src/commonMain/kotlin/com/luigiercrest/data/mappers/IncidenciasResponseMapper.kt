package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.IncidenciaDTO
import com.luigiercrest.domain.models.IncidenciaResponseModel

object IncidenciasResponseMapper {
    fun map(incidencias: List<IncidenciaDTO>, status: Int): List<IncidenciaResponseModel> {
        return incidencias.map { incidenciaDTO ->
            IncidenciaResponseModel(
                idIncidencia = incidenciaDTO.idIncidencia,
                idCentro = incidenciaDTO.idCentro,
                idDispositivo = incidenciaDTO.idDispositivo,
                idServicioTecnico = incidenciaDTO.idServicioTecnico,
                dniResponsable = incidenciaDTO.dniResponsable,
                descripcion = incidenciaDTO.descripcion,
                fechaReporte = incidenciaDTO.fechaReporte,
                fechaCierre = incidenciaDTO.fechaCierre,
                estado = incidenciaDTO.estado,
                statusCode = status
            )
        }
    }
}