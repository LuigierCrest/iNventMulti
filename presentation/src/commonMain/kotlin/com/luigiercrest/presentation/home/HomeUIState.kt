package com.luigiercrest.presentation.home

import com.luigiercrest.domain.models.CentroResponseModel

data class HomeUIState (
    val centro: CentroResponseModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)




