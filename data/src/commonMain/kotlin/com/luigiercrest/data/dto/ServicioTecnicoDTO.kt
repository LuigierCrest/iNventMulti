package com.luigiercrest.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class ServicioTecnicoDTO(
    val idServicioTecnico: Int,
    val nombre: String,
    val direccion: String?,
    val telefono: Int?,
    val email: String?
) {
}