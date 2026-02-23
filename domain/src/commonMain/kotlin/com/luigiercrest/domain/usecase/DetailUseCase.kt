package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.DeleteResponseModel
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
}

