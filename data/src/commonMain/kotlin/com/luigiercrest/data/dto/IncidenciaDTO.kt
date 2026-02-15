package com.luigiercrest.data.dto

data class IncidenciaDTO(
    val idIncidencia: Int,
    val idCentro: Int,
    val idDispositivo: Int,
    val idServicioTecnico: Int,
    val dniResponsable: String,
    val descripcion: String,
    val fechaReporte: String, //FECHA
    val fechaCierre: String, //FECHA
    val estado: String
) {
}