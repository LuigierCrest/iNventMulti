package com.luigiercrest.inventmulti.ui


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.inventmulti.models.CategoryModel
import com.luigiercrest.inventmulti.roles.RoleCategories
import com.luigiercrest.inventmulti.ui.widgets.cards.CategoryCard
import com.luigiercrest.presentation.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onCategoryClick: (CategoryModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val rol = viewModel.rol.collectAsState()
    val categorias = RoleCategories.getCategoriesByRole(rol.value)

    // Bloquea el botón de retroceso del sistema
    // BackHandler(enabled = true) {}

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(

                    title = { Text("iNvent") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    actions = {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert, contentDescription = "Menú"
                            )
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(text = { Text("Cambiar contraseña") }, onClick = {
                                // Acción para cambiar contraseña
                                menuExpanded = false
                            })
                            DropdownMenuItem(text = { Text("Logout") }, onClick = {
                                // Acción para cambiar contraseña
                                menuExpanded = false
                            })
                            DropdownMenuItem(text = { Text("Sobre la APP") }, onClick = {
                                // Acción para mostrar info de la APP
                                menuExpanded = false
                            })
                        }
                    }
                )
            }, modifier = modifier
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Hay una clase modelo con las categorías que deben aparecer según el rol
                item {
                    val state = viewModel.state.collectAsState()
                    val centro = state.value.centro
                    val mostrarRol = when (rol.value) {
                        "ADMIN" -> "Administrador/a"
                        "DIRE" -> "Director/a"
                        "RESP" -> "Responsable"
                        else -> rol.value
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    // Rol del usuario
                    Text(text = mostrarRol, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                    // Centro del usuario
                    Text(
                        text = if (centro != null) "${centro.tipo} ${centro.nombre}" else "Centro no disponible",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                items(items = categorias) { category ->
                    CategoryCard(
                        category = category,
                        onCategoryClick = {
                            onCategoryClick(category)
                        },
                        modifier = Modifier
                    )

                }
            }
        }
    }

}
