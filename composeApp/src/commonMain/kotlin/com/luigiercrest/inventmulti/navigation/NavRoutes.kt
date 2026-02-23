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
    @Serializable
    data class Detail(val categoryID: Int, val item: Int? = null) :  NavRoutes, NavKey

    @Serializable
    data object AppInfo: NavRoutes, NavKey

    @Serializable
    data class CreateUser(val categoryID: Int) :  NavRoutes, NavKey
}