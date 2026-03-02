package com.luigiercrest.inventmulti.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.inventmulti.models.CategoryModel
import com.luigiercrest.inventmulti.navigation.RefreshEventAfterDelOrUpdate
import com.luigiercrest.inventmulti.scanner.BarcodeScannerScreen
import com.luigiercrest.inventmulti.ui.widgets.cards.AsignacionCard
import com.luigiercrest.inventmulti.ui.widgets.cards.CentroCard
import com.luigiercrest.inventmulti.ui.widgets.cards.DispositivoCard
import com.luigiercrest.inventmulti.ui.widgets.cards.IncidenciaCard
import com.luigiercrest.inventmulti.ui.widgets.cards.ProveedorCard
import com.luigiercrest.inventmulti.ui.widgets.cards.ServicioTecnicoCard
import com.luigiercrest.inventmulti.ui.widgets.cards.UsuarioCard
import com.luigiercrest.inventmulti.utils.normalize
import com.luigiercrest.presentation.category.CategoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    category: CategoryModel,
    onItemClick: (Int, Any) -> Unit,
    onCreateClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CategoryViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var showScanner by remember { mutableStateOf(false) }

    LaunchedEffect(category.idCategoria) {
        viewModel.setCategoryId(category.idCategoria)
    }
    LaunchedEffect(category.idCategoria){
        RefreshEventAfterDelOrUpdate.refreshTrigger.collect {
            viewModel.setCategoryId(category.idCategoria)
        }
    }

    // Mostrar escáner a pantalla completa cuando se activa
    if (showScanner) {
        BarcodeScannerScreen(
            onScanned = { code ->
                searchQuery = code
                showScanner = false
            },
            onCancelled = {
                showScanner = false
            }
        )
        return
    }

    Screen {
        Scaffold(
            topBar = {
                Column {
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
                        },
                        actions = {
                            IconButton(onClick = { isSearchActive = !isSearchActive }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }

                    )
                    AnimatedVisibility(visible = isSearchActive) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            placeholder = { Text("Buscar...") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar"
                                )
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Limpiar"
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }
            },
            // FABs nuevo elemento y escáner de código de barras
            floatingActionButton = {
                Column (
                    horizontalAlignment = androidx.compose.ui.Alignment.End,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)

                ) {
                    // FAB de código de barras solo para categorías 6 y 9
                    if (category.idCategoria == 6 || category.idCategoria == 9) {
                        FloatingActionButton(
                            onClick = { showScanner = true },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ) {
                            Icon(
                                imageVector = Icons.Default.QrCodeScanner,
                                contentDescription = "Escanear código de barras"
                            )
                        }
                    }

                    // FAB + para nuevo registro
                    // oculto para 7, 9 y 10
                    if (category.idCategoria != 7 && category.idCategoria != 9 && category.idCategoria != 10) {
                        FloatingActionButton(
                            onClick = { onCreateClick(category.idCategoria) },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Añadir"
                            )
                        }
                    }
                }
            },
            modifier = modifier.fillMaxSize()
        ) { padding ->
            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = { viewModel.setCategoryId(category.idCategoria)},
                modifier = Modifier.fillMaxSize()
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(padding)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val query = searchQuery.normalize()
                        // Mostrar items según la categoría y la búsqueda
                        when (category.idCategoria) {
                            1 -> {
                                val filtered = if (query.isBlank()) state.centros else state.centros.filter {
                                    "${it.tipo} ${it.nombre} ${it.idCentro} ${it.municipio}".normalize().contains(query)
                                }
                                items(filtered) { centro ->
                                    CentroCard(
                                        centro = centro,
                                        onCentroClick = { selectedCentro ->
                                            onItemClick(category.idCategoria, selectedCentro)}
                                    )
                                }
                            }

                            2 -> {
                                val filtered = if (query.isBlank()) state.proveedores else state.proveedores.filter {
                                    "${it.nombre} ${it.direccion}".normalize().contains(query)
                                }
                                items(filtered) { proveedor ->
                                    ProveedorCard(
                                        proveedor = proveedor,
                                        onProveedorClick = { selectedProveedor ->
                                            onItemClick(category.idCategoria, selectedProveedor)
                                        }
                                    )
                                }
                            }

                            3 -> {
                                val filtered = if (query.isBlank()) state.servicios else state.servicios.filter {
                                    "${it.nombre} ${it.direccion}".normalize().contains(query)
                                }
                                items(filtered) { servicioTecnico ->
                                    ServicioTecnicoCard(
                                        servicioTecnico = servicioTecnico,
                                        onServicioTecnicoClick = { selectedServicioTecnico ->
                                            onItemClick(category.idCategoria, selectedServicioTecnico)
                                        }
                                    )
                                }
                            }

                            4 -> {
                                val filtered = if (query.isBlank()) state.asignaciones else state.asignaciones.filter {
                                    "${it.idAsignacion} ${it.entrega} ${it.idCentro}".normalize().contains(query)
                                }
                                items(filtered) { asignacion ->
                                    AsignacionCard(
                                        asignacion = asignacion,
                                        onAsignacionClick = { selectedAsignacion ->
                                            onItemClick(category.idCategoria, selectedAsignacion)
                                        }
                                    )
                                }

                            }

                            5, 8 -> {
                                val filtered = if (query.isBlank()) state.usuarios else state.usuarios.filter {
                                    "${it.nombre} ${it.apellidos} ${it.dni} ${it.rol} ${it.idCentro} ${it.departamento}".normalize().contains(query)
                                }
                                items(filtered) { usuario ->
                                    UsuarioCard(
                                        usuario = usuario,
                                        onUsuarioClick = { selectedUsuario ->
                                            onItemClick(category.idCategoria, selectedUsuario)
                                        }
                                    )
                                }
                            }

                            6, 9 -> {
                                val filtered = if (query.isBlank()) state.dispositivos else state.dispositivos.filter {
                                    "${it.idDispositivo} ${it.idCentro} ${it.nombre} ${it.numSerie} ${it.marcaModelo} ${it.ultimaActualizacion} ${it.observaciones} ${it.categoria} ${it.estado} ${it.uso} ${it.ubicacion} ${it.idAsignacion}".normalize().contains(query)
                                }
                                items(filtered) { dispositivo ->
                                    DispositivoCard(
                                        dispositivo = dispositivo,
                                        onDispositivoClick = { selectedDispositivo ->
                                            println("LOG - Dispositivo seleccionado: ${selectedDispositivo.categoria}")
                                            onItemClick(category.idCategoria, selectedDispositivo)
                                        }
                                    )
                                }
                            }

                            7, 10 -> {
                                val filtered = if (query.isBlank()) state.incidencias else state.incidencias.filter {
                                    "${it.idIncidencia} ${it.idCentro} ${it.idDispositivo} ${it.dniResponsable} ${it.descripcion} ${it.fechaReporte} ${it.fechaCierre} ${it.estado}".normalize().contains(query)
                                }
                                items(filtered) { incidencia ->
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
    }

}

