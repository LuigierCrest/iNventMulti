package com.luigiercrest.inventmulti.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.luigiercrest.inventmulti.models.CategoryModel
import com.luigiercrest.inventmulti.navigation.NavRoutes
import com.luigiercrest.inventmulti.ui.widgets.cards.AsignacionCard
import com.luigiercrest.inventmulti.ui.widgets.cards.CentroCard
import com.luigiercrest.inventmulti.ui.widgets.cards.DispositivoCard
import com.luigiercrest.inventmulti.ui.widgets.cards.IncidenciaCard
import com.luigiercrest.inventmulti.ui.widgets.cards.ProveedorCard
import com.luigiercrest.inventmulti.ui.widgets.cards.ServicioTecnicoCard
import com.luigiercrest.inventmulti.ui.widgets.cards.UsuarioCard
import com.luigiercrest.presentation.category.CategoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    category: CategoryModel,
    onItemClick: (Int, Any) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CategoryViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(category.idCategoria) {
        viewModel.setCategoryId(category.idCategoria)
    }

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(category.categoria) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    navigationIcon = {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            },
            modifier = modifier.fillMaxSize()
        ) { padding ->
            Column (
                modifier = Modifier.fillMaxWidth().padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    // Mostrar items según la categoría
                    when (category.idCategoria) {
                        1 -> items(state.centros) { centro ->
                            CentroCard(
                                centro = centro,
                                onCentroClick = { selectedCentro ->
                                    onItemClick(category.idCategoria, selectedCentro)}
                            )
                        }

                        2 -> items(state.proveedores) { proveedor ->
                            ProveedorCard(
                                proveedor = proveedor,
                                onProveedorClick = { selectedProveedor ->
                                    onItemClick(category.idCategoria, selectedProveedor)
                                }
                            )
                        }

                        3 -> items(state.servicios) { servicioTecnico ->
                            ServicioTecnicoCard(
                                servicioTecnico = servicioTecnico,
                                onServicioTecnicoClick = { selectedServicioTecnico ->
                                    onItemClick(category.idCategoria, selectedServicioTecnico)
                                }
                            )
                        }

                        4 -> items(state.asignaciones) { asignacion ->
                            AsignacionCard(
                                asignacion = asignacion,
                                onAsignacionClick = { selectedAsignacion ->
                                    onItemClick(category.idCategoria, selectedAsignacion)
                                }
                            )
                        }

                        5, 8 -> items(state.usuarios) { usuario ->
                            UsuarioCard(
                                usuario = usuario,
                                onUsuarioClick = { selectedUsuario ->
                                    onItemClick(category.idCategoria, selectedUsuario)
                                }
                            )
                        }

                        6, 9 -> items(state.dispositivos) { dispositivo ->
                            DispositivoCard(
                                dispositivo = dispositivo,
                                onDispositivoClick = { selectedDispositivo ->
                                    println("LOG - Dispositivo seleccionado: ${selectedDispositivo.categoria}")
                                    onItemClick(category.idCategoria, selectedDispositivo)
                                }
                            )
                        }

                        7, 10 -> items(state.incidencias) { incidencia ->
                            IncidenciaCard(
                                incidencia = incidencia,
                                onIncidenciaClick = { selectedIncidencia ->
                                    onItemClick(category.idCategoria, selectedIncidencia)
                                }
                            )
                        }

                    }

                }
            }

        }
    }

}

