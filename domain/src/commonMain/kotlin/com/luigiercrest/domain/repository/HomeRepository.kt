package com.luigiercrest.domain.repository

import com.luigiercrest.domain.models.CentroResponseModel

interface HomeRepository {
    suspend fun getCentroHome(idCentro: String, token: String): Result<CentroResponseModel>
}
