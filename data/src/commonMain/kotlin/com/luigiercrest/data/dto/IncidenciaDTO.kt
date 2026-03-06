package com.luigiercrest.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class IncidenciaDTO(
    val idIncidencia: Int? = null,
    val idCentro: Int,
    val idDispositivo: Int,
    val idServicioTecnico: Int,
    val dniResponsable: String,
    val descripcion: String,
    val fechaReporte: String?=null, //FECHA
    val fechaCierre: String?=null, //FECHA
    val estado: String
) {
}