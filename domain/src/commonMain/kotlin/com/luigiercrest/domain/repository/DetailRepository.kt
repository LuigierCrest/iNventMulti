package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.AsignacionModel
import com.luigiercrest.domain.models.CentroModel
import com.luigiercrest.domain.models.DeleteResponseModel
import com.luigiercrest.domain.models.DispositivoModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ServicioTecnicoModel
import com.luigiercrest.domain.models.UpdateResponseModel
import com.luigiercrest.domain.models.UsuarioModel

interface DetailRepository {
    suspend fun deleteDispositivo(token: String, idDispositivo: Int): Result<DeleteResponseModel>
    suspend fun deleteIncidencia(token: String, idIncidencia: Int): Result<DeleteResponseModel>
    suspend fun deleteUsuario(token: String, dniUsuario: String): Result<DeleteResponseModel>
    suspend fun deleteCentro (token: String, idCentro : Int): Result<DeleteResponseModel>
    suspend fun deleteProveedor (token: String, idProveedor:Int): Result<DeleteResponseModel>
    suspend fun deleteServicioTecnico(token: String, idServicioTecnico : Int): Result<DeleteResponseModel>
    suspend fun deleteAsignacion(token: String, idAsignacion : Int): Result<DeleteResponseModel>

    suspend fun updateCentro(token: String, centroModel: CentroModel): Result<UpdateResponseModel>
    suspend fun updateProveedor(token: String, proveedorModel: ProveedorModel): Result<UpdateResponseModel>
    suspend fun updateUsuario(token: String, usuarioModel: UsuarioModel): Result<UpdateResponseModel>
    suspend fun updateServicioTecnico(token: String, servicioTecnicoModel: ServicioTecnicoModel) : Result<UpdateResponseModel>
    suspend fun updateAsignacion(token: String, asignacionModel: AsignacionModel): Result<UpdateResponseModel>
    suspend fun updateDispositivo(token: String, dispositivoModel: DispositivoModel): Result<UpdateResponseModel>
    suspend fun updateIncidencia(token: String, incidenciaModel: IncidenciaModel): Result<UpdateResponseModel>
}

