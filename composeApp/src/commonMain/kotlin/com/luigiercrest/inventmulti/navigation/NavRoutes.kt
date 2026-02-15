package com.luigiercrest.inventmulti.navigation


import androidx.navigation3.runtime.NavKey
import com.luigiercrest.inventmulti.models.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes: NavKey {
    @Serializable
    data object Login : NavRoutes, NavKey

    @Serializable
    data object Home: NavRoutes, NavKey

    @Serializable
    data class Category(val category: CategoryModel): NavRoutes, NavKey {
    }
}