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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.luigiercrest.inventmulti.ui.AppInfoScreen
import com.luigiercrest.inventmulti.ui.CategoryScreen
import com.luigiercrest.inventmulti.ui.ChangePasswdScreen
import com.luigiercrest.inventmulti.ui.DetailScreen
import com.luigiercrest.inventmulti.ui.HomeScreen
import com.luigiercrest.inventmulti.ui.LoginScreen
import com.luigiercrest.inventmulti.ui.create.CreateIncidenciaScreen
import com.luigiercrest.inventmulti.ui.create.CreateUsuarioScreen
import com.luigiercrest.presentation.changePassword.ChangePasswordViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

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
                    subclass(NavRoutes.CreateIncidencia::class, NavRoutes.CreateIncidencia.serializer())
                    subclass(NavRoutes.ChangePassword::class, NavRoutes.ChangePassword.serializer())
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
                onChangePasswordClick = {
                    backStack.add(NavRoutes.ChangePassword)
                },
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
                        if (isMultiPane) {
                            // En multipane: solo actualizar el item, no remontar DetailScreen
                            // Si no hay Detail aún en el backStack, añadirlo
                            if (backStack.lastOrNull() !is NavRoutes.Detail) {
                                backStack.add(NavRoutes.Detail(categoryId))
                            }
                        } else {
                            // En single pane: eliminar Detail previo si existe y añadir nuevo
                            if (backStack.lastOrNull() is NavRoutes.Detail) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                            backStack.add(NavRoutes.Detail(categoryId))
                        }
                    },
                    onCreateClick = { categoryId ->

                        when (categoryId) {
//                            1 ->  backStack.add(NavRoutes.CreateCentro(categoryId)) // nuevo centro
//                            2 ->  backStack.add(NavRoutes.CreateProveedor(categoryId)) // nuevo proveedor
//                            3 ->  backStack.add(NavRoutes.CreateServicioTecnico(categoryId)) // nuevo servicio técnico
//                            4 ->  backStack.add(NavRoutes.CreateAsignacion(categoryId)) // nuevo asignación
                            5, 8 -> backStack.add(NavRoutes.CreateUser(categoryId)) // nuevo usuario (admin o dire)
//                            6, 9 -> backStack.add(NavRoutes.CreateDispositivo(categoryId)) // nueva dispositivo
//                            7, 10 -> backStack.add(NavRoutes.CreateIncidencia(categoryId)) // nuevo incidencia
                        }
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
                    },
                    onDeleted = {
                        // navegar atrás y refrescar la lista tras eliminar un elemento
                        if (backStack.isNotEmpty()){
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    onUpdated = {
                        // En pantalla estrecha vuelve a CategoryScreen para recargar la lista
                        // DetailScreen ya llama a onUpdated solo cuando !isMultiPane
                        if (backStack.isNotEmpty()) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    onCreateIncidencia = { category, idDispositivo, idCentro ->
                        backStack.add(
                            NavRoutes.CreateIncidencia(
                                key.categoryID,
                                idDispositivo,
                                idCentro
                            )
                        )
                    }
                )
            }

            // Cambiar contraseña
            entry<NavRoutes.ChangePassword> {
                val changePasswordViewModel: ChangePasswordViewModel = koinViewModel()
                val state = changePasswordViewModel.state.collectAsState()
                ChangePasswdScreen(
                    onBackClick = { backStack.removeAt(backStack.lastIndex) },
                    onPasswordChanged = { newPassword ->
                        changePasswordViewModel.changePassword(newPassword)
                    },
                    state = state.value,
                    onSuccessNavigate = {
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
                CreateUsuarioScreen(
                    categoryId = key.categoryID,
                    onBackClick = { backStack.removeAt(backStack.lastIndex) }
                )
            }

            // Crear incidencia
            entry<NavRoutes.CreateIncidencia> { key ->
                CreateIncidenciaScreen(
                    categoryId = key.categoryID,
                    idDispositivo = key.idDispositivo,
                    idCentro = key.idCentro,
                    onBackClick = { backStack.removeAt(backStack.lastIndex) }
                )
            }

        }

    )

}

