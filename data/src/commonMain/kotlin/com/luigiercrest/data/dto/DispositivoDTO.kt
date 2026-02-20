package com.luigiercrest.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class DispositivoDTO(
    val idDispositivo: Int,
    val idCentro: Int,
    val nombre: String,
    val numSerie: String?,
    val marcaModelo: String?,
    val ultimaActualizacion: String?,//FECHA
    val observaciones: String?,
    val estado: String?,
    val categoria: String?,
    val ubicacion: String?,
    val uso: String?,
    val idAsignacion: Int
) {
}