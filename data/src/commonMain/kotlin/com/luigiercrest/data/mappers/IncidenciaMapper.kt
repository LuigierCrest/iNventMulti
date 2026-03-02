package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.IncidenciaDTO
import com.luigiercrest.domain.models.IncidenciaModel

object IncidenciaMapper {
    fun toDTO (incidenciaModel: IncidenciaModel) : IncidenciaDTO {
        return IncidenciaDTO(
            idIncidencia = incidenciaModel.idIncidencia ?: 0,
            idCentro = incidenciaModel.idCentro ?: 0,
            idDispositivo = incidenciaModel.idDispositivo ?: 0,
            idServicioTecnico = incidenciaModel.idServicioTecnico ?: 0,
            dniResponsable = incidenciaModel.dniResponsable ?: "",
            descripcion = incidenciaModel.descripcion ?: "",
            fechaReporte = incidenciaModel.fechaReporte ?: "",
            fechaCierre = incidenciaModel.fechaCierre ?: "",
            estado = incidenciaModel.estado ?: ""
        )
    }
}