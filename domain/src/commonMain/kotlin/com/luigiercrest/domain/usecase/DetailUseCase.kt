package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.AsignacionModel
import com.luigiercrest.domain.models.CentroModel
import com.luigiercrest.domain.models.DeleteResponseModel
import com.luigiercrest.domain.models.DispositivoModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ServicioTecnicoModel
import com.luigiercrest.domain.models.UpdateResponseModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.repository.DetailRepository


class DetailUseCase (private val detailRepository: DetailRepository){
    suspend fun deleteDispositivo(token: String, idDispositivo: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteDispositivo(token, idDispositivo)
    }
    suspend fun deleteIncidencia(token: String, idIncidencia: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteIncidencia(token, idIncidencia)
    }
    suspend fun deleteUsuario(token: String, dniUsuario: String): Result<DeleteResponseModel> {
        return detailRepository.deleteUsuario(token, dniUsuario)
    }
    suspend fun deleteCentro(token: String, idCentro: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteCentro(token, idCentro)
    }

    suspend fun deleteProveedor(token: String, idProveedor: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteProveedor(token, idProveedor)
    }

    suspend fun deleteServicioTecnico(token: String, idServicioTecnico: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteServicioTecnico(token, idServicioTecnico)
    }

    suspend fun deleteAsignacion(token: String, idAsignacion: Int): Result<DeleteResponseModel> {
        return detailRepository.deleteAsignacion(token, idAsignacion)
    }

    suspend fun updateCentro(token: String, centroModel: CentroModel): Result<UpdateResponseModel> {
        return detailRepository.updateCentro(token, centroModel)
    }

    suspend fun updateProveedor(token: String, proveedorModel: ProveedorModel): Result<UpdateResponseModel> {
        return detailRepository.updateProveedor(token, proveedorModel)
    }

    suspend fun updateUsuario(token: String, usuarioModel: UsuarioModel): Result<UpdateResponseModel> {
        return detailRepository.updateUsuario(token, usuarioModel)
    }

    suspend fun updateServicioTecnico(token: String, servicioTecnicoModel: ServicioTecnicoModel): Result<UpdateResponseModel> {
        return detailRepository.updateServicioTecnico(token, servicioTecnicoModel)
    }

    suspend fun updateAsignacion(token: String, asignacionModel: AsignacionModel): Result<UpdateResponseModel> {
        return detailRepository.updateAsignacion(token, asignacionModel)
    }

    suspend fun updateDispositivo(token: String, dispositivoModel: DispositivoModel): Result<UpdateResponseModel> {
        return detailRepository.updateDispositivo(token,dispositivoModel)
    }

    suspend fun updateIncidencia(token: String, incidenciaModel: IncidenciaModel): Result<UpdateResponseModel> {
        return detailRepository.updateIncidencia(token, incidenciaModel)
    }

}

