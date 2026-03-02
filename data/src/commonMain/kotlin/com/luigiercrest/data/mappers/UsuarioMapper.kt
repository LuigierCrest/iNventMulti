package com.luigiercrest.data.mappers

import com.luigiercrest.data.database.dto.UsuarioDTO
import com.luigiercrest.domain.models.UsuarioModel

object UsuarioMapper {
    fun toDTO(usuarioModel: UsuarioModel): UsuarioDTO {
        return UsuarioDTO(
            idUsuario = usuarioModel.idUsuario ?: 0,
            dni = usuarioModel.dni ?: "",
            idCentro = usuarioModel.idCentro ?: 0,
            nombre = usuarioModel.nombre ?: "",
            apellidos = usuarioModel.apellidos ?: "",
            email = usuarioModel.email ?: "",
            departamento = usuarioModel.departamento ?: "",
            rol = usuarioModel.rol ?: "",
            passwdHash = usuarioModel.passwdHash ?: ""
        )
    }
}