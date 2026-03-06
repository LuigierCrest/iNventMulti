package com.luigiercrest.data.mappers

import com.luigiercrest.data.database.dto.UsuarioDTO
import com.luigiercrest.domain.models.UsuarioResponseModel

object UsuariosResponseMapper {
    fun map (usuarios: List<UsuarioDTO>, status: Int): List<UsuarioResponseModel> {
        return usuarios.map { usuarioDTO ->
            UsuarioResponseModel(
                idUsuario = usuarioDTO.idUsuario,
                dni = usuarioDTO.dni,
                idCentro = usuarioDTO.idCentro,
                nombre = usuarioDTO.nombre,
                apellidos = usuarioDTO.apellidos,
                email = usuarioDTO.email,
                departamento = usuarioDTO.departamento,
                rol = usuarioDTO.rol,
                statusCode = status
            )
        }
    }
    fun toModel (usuarioDTO: UsuarioDTO, status: Int): UsuarioResponseModel {
        return UsuarioResponseModel(
            idUsuario = usuarioDTO.idUsuario,
            dni = usuarioDTO.dni,
            idCentro = usuarioDTO.idCentro,
            nombre = usuarioDTO.nombre,
            apellidos = usuarioDTO.apellidos,
            email = usuarioDTO.email,
            departamento = usuarioDTO.departamento,
            rol = usuarioDTO.rol,
            statusCode = status
        )
    }

}