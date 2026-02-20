package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.DispositivoDTO
import com.luigiercrest.domain.models.DispositivoResponseModel

object DispositivosResponseMapper {
    fun map (dispositivos: List<DispositivoDTO>, status: Int): List<DispositivoResponseModel> {
        return dispositivos.map { dispositivoDTO ->
            DispositivoResponseModel(
                idDispositivo = dispositivoDTO.idDispositivo,
                idCentro = dispositivoDTO.idCentro,
                nombre = dispositivoDTO.nombre,
                numSerie = dispositivoDTO.numSerie,
                marcaModelo = dispositivoDTO.marcaModelo,
                ultimaActualizacion = dispositivoDTO.ultimaActualizacion,
                observaciones = dispositivoDTO.observaciones,
                estado = dispositivoDTO.estado,
                categoria = dispositivoDTO.categoria,
                ubicacion = dispositivoDTO.ubicacion,
                uso = dispositivoDTO.uso,
                idAsignacion = dispositivoDTO.idAsignacion,
                statusCode = status
            )
        }
    }

}