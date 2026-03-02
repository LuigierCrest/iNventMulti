package com.luigiercrest.data.mappers

import com.luigiercrest.data.dto.ProveedorDTO
import com.luigiercrest.domain.models.ProveedorModel

object ProveedorMapper {
    fun toDTO(proveedorModel: ProveedorModel): ProveedorDTO {
        return ProveedorDTO(
            idProveedor = proveedorModel.idProveedor ?: 0,
            nombre = proveedorModel.nombre ?: "",
            direccion = proveedorModel.direccion ?: "",
            telefono = proveedorModel.telefono ?: "",
            email = proveedorModel.email ?: ""
        )
    }
}