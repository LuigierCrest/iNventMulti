package com.luigiercrest.presentation.create

import com.luigiercrest.domain.models.ServicioTecnicoResponseModel

data class CreateUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val serverMessage: String? = null,
    val serviciosTecnicos: List<ServicioTecnicoResponseModel> = emptyList()
)
