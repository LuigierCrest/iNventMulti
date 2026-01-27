package com.luigiercrest.data.database.dto


data class UsuarioDTO (
    val dni: String,
    val idUsuario: Int,
    val idCentro: Int,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val departamento: String,
    val rol: String,
    val password: String
) {



}