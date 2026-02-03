package com.luigiercrest.inventmulti.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.luigiercrest.inventmulti.ui.HomeScreen
import com.luigiercrest.inventmulti.ui.LoginScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavRoot () {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule{
                polymorphic(NavKey::class){
                    subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())
                    subclass(NavRoutes.Home::class, NavRoutes.Home.serializer())
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
                is NavRoutes.Home -> NavEntry(key){ HomeScreen(backStack = backStack) }
                else -> error("NavRoute deconocida: $key")
            }

        }
    )
}
