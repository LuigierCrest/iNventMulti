package com.luigiercrest.presentation.category

import com.luigiercrest.domain.models.CategoryModel

data class CategoryUIState(
    val categories: List<CategoryModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
