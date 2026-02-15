package com.luigiercrest.data.dto

data class DispositivoDTO(
    val idDispositivo: Int,
    val idCentro: Int,
    val nombre: String,
    val numSerie: String,
    val marcaModelo: String,
    val ultimmaActualizacion: String,//FECHA
    val obsercaciones: String,
    val estado: String,
    val categoria: String,
    val ubicacion: String,
    val uso: String,
    val idAsignacion: Int
) {
}