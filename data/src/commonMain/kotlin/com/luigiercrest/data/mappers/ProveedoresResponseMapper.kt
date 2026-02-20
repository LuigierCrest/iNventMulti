package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.ProveedorDTO
import com.luigiercrest.domain.models.ProveedorResponseModel

object ProveedoresResponseMapper {
    fun map(proveedores: List<ProveedorDTO>, status: Int): List<ProveedorResponseModel> {
        return proveedores.map { proveedorDTO ->
            ProveedorResponseModel(
                idProveedor = proveedorDTO.idProveedor,
                nombre = proveedorDTO.nombre,
                direccion = proveedorDTO.direccion,
                telefono = proveedorDTO.telefono,
                email = proveedorDTO.email,
                statusCode = status
            )
        }
    }
}