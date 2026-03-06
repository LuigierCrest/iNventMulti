package com.luigiercrest.domain.models

data class IncidenciaModel(
    val idIncidencia: Int?,
    val idCentro: Int?,
    val idDispositivo: Int?,
    val idServicioTecnico: Int?,
    val dniResponsable: String?,
    val descripcion: String?,
    val fechaReporte: String?=null, //FECHA
    val fechaCierre: String?=null, //FECHA
    val estado: String?
)
