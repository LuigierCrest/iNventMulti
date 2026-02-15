package com.luigiercrest.inventmulti.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.luigiercrest.inventmulti.ui.CategoryScreen
import com.luigiercrest.inventmulti.ui.HomeScreen
import com.luigiercrest.inventmulti.ui.LoginScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun NavRoot () {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule{
                polymorphic(NavKey::class){
                    subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())
                    subclass(NavRoutes.Home::class, NavRoutes.Home.serializer())
                    subclass(NavRoutes.Category::class, NavRoutes.Category.serializer())
                }
            }
        },
        NavRoutes.Login
    )

    NavDisplay(
        backStack = backStack,
        entryProvider = { key ->
            when(key){
                is NavRoutes.Login -> NavEntry(key){ LoginScreen(backStack = backStack) }
                is NavRoutes.Home -> NavEntry(key){ HomeScreen(
                    onCategoryClick = { category ->
                        // Navega a la CategoryScreen pasando la categoría
                        backStack.add(NavRoutes.Category(category))
                    }
                ) }
                is NavRoutes.Category -> NavEntry(key){
                    CategoryScreen(
                        category = key.category,
                        onBackClick = { backStack.removeLast() }
                    )
                }
                else -> error("NavRoute deconocida: $key")
            }

        }
    )
}
