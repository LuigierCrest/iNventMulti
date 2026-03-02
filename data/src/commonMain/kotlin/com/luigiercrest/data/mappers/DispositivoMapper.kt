package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.DispositivoDTO
import com.luigiercrest.domain.models.DispositivoModel

object DispositivoMapper {
    fun toDTO(dispositivoModel: DispositivoModel): DispositivoDTO {
        return DispositivoDTO(
            idDispositivo = dispositivoModel.idDispositivo ?: 0,
            idCentro = dispositivoModel.idCentro ?: 0,
            nombre = dispositivoModel.nombre ?: "",
            numSerie = dispositivoModel.numSerie ?: "",
            marcaModelo = dispositivoModel.marcaModelo ?: "",
            ultimaActualizacion = dispositivoModel.ultimaActualizacion ?: "",
            observaciones = dispositivoModel.observaciones ?: "",
            estado = dispositivoModel.estado ?: "",
            ubicacion = dispositivoModel.ubicacion ?: "",
            uso = dispositivoModel.uso ?: "",
            categoria = dispositivoModel.categoria?: "",
            idAsignacion = dispositivoModel.idAsignacion?: 0
        )
    }
}