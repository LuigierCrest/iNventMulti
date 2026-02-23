package com.luigiercrest.inventmulti.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.luigiercrest.inventmulti.ui.AppInfoScreen
import com.luigiercrest.inventmulti.ui.CategoryScreen
import com.luigiercrest.inventmulti.ui.DetailScreen
import com.luigiercrest.inventmulti.ui.HomeScreen
import com.luigiercrest.inventmulti.ui.LoginScreen
import com.luigiercrest.inventmulti.ui.create.CreateUserScreen
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
                    subclass(NavRoutes.Detail::class, NavRoutes.Detail.serializer())
                    subclass(NavRoutes.AppInfo::class, NavRoutes.AppInfo.serializer())
                    subclass(NavRoutes.CreateUser::class, NavRoutes.CreateUser.serializer())
                }
            }
        },
        NavRoutes.Login
    )
    // Info para Screen adaptables
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(
        directive = calculatePaneScaffoldDirective(adaptiveInfo).copy(horizontalPartitionSpacerSize = 0.dp),

        )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        sceneStrategy = listDetailStrategy,
        entryProvider = entryProvider {
            // Pantallas fullscreen
            entry<NavRoutes.Login> {LoginScreen(backStack = backStack)}
            entry<NavRoutes.Home> {HomeScreen(
                onCategoryClick = {backStack.add(NavRoutes.Category(it))},
                onAppInfoClick = {
                    backStack.add(NavRoutes.AppInfo)
                }
                //Logout
            )}
            // Cambiar contraseña
            // About

            // Pantalla de lista
            entry<NavRoutes.Category> (
                metadata = ListDetailSceneStrategy.listPane(),
            ) { key ->
                val directive = calculatePaneScaffoldDirective(adaptiveInfo)
                val isMultiPane = directive.maxHorizontalPartitions > 1
                CategoryScreen(
                    category = key.category,
                    onItemClick = { categoryId, item ->
                        SharedItemHolder.setItem(item)
                        // Elimina Detail previo antes de añadir otro nuevo
                        if (backStack.lastOrNull() is NavRoutes.Detail) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                        backStack.add(NavRoutes.Detail(categoryId))
                    },
                    onCreateClick = { categoryId ->
                        backStack.add(NavRoutes.CreateUser(categoryId))
                    },
                    modifier = Modifier.fillMaxSize(),

                    onBackClick = {
                        if (isMultiPane) {
                            // cierra la pantalla de detalle
                            while (backStack.lastOrNull() is NavRoutes.Detail || backStack.lastOrNull() is NavRoutes.Category) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        } else {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }
            // Pantalla detalle
            entry<NavRoutes.Detail>(
                metadata = ListDetailSceneStrategy.detailPane()
            ) { key ->
                val directive = calculatePaneScaffoldDirective(adaptiveInfo)
                val isMultiPane = directive.maxHorizontalPartitions > 1
                DetailScreen(
                    categoryId = key.categoryID,
                    item = SharedItemHolder.selectedItem.collectAsState().value,
                    isMultiPane = isMultiPane,
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = {
                        backStack.removeAt(backStack.lastIndex)
                    }
                )
            }

            // Pantalla de info
            entry<NavRoutes.AppInfo> {
                AppInfoScreen(
                    onBackClick = { backStack.removeAt(backStack.lastIndex) }
                )
            }

            // Crear usuario
            entry<NavRoutes.CreateUser> { key ->
                CreateUserScreen(
                    categoryId = key.categoryID,
                    onBackClick = { backStack.removeAt(backStack.lastIndex) }
                )
            }

        }

    )

}
