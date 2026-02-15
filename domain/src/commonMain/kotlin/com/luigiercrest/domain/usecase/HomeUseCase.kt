package com.luigiercrest.domain.usecase

import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.repository.HomeRepository

class HomeUseCase(private val homeRepository: HomeRepository) {
    suspend fun getCentroHome(idCentro: String, token: String): Result<CentroResponseModel> {
        return homeRepository.getCentroHome(idCentro, token)
    }
}