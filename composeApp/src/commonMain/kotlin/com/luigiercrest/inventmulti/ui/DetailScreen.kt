package com.luigiercrest.inventmulti.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.AsignacionModel
import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.inventmulti.navigation.RefreshEventAfterDelOrUpdate
import com.luigiercrest.inventmulti.ui.details.AsignacionDetails
import com.luigiercrest.inventmulti.ui.details.CentroDetails
import com.luigiercrest.inventmulti.ui.details.DispositivoDetails
import com.luigiercrest.inventmulti.ui.details.IncidenciaDetails
import com.luigiercrest.inventmulti.ui.details.ProveedorDetails
import com.luigiercrest.inventmulti.ui.details.ServicioTecnicoDetails
import com.luigiercrest.inventmulti.ui.details.UsuarioDetails
import com.luigiercrest.inventmulti.navigation.SharedItemHolder
import com.luigiercrest.inventmulti.utils.CategoryIconMapper
import com.luigiercrest.inventmulti.utils.DateUtil.getCurrentDate
import com.luigiercrest.inventmulti.utils.DeviceIconMapper
import com.luigiercrest.presentation.detail.DeleteState
import com.luigiercrest.presentation.detail.DetailViewModel
import com.luigiercrest.presentation.detail.UpdateState
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    categoryId: Int,
    item: Any?,
    isMultiPane: Boolean = false,
    viewModel: DetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onDeleted: () -> Unit,
    onUpdated: () -> Unit,
    onCreateIncidencia: (categoryId: Int, idDispositivo: Int, idCentro: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Observar el item y la versión del SharedItemHolder
    val currentItem by SharedItemHolder.selectedItem.collectAsState()
    val currentVersion by SharedItemHolder.version.collectAsState()

    // Usar el item del SharedItemHolder si estamos en multiPane, si no el parámetro directo
    val effectiveItem = if (isMultiPane) currentItem else item

    LaunchedEffect(currentVersion) {
        viewModel.clearState()
        val itemToLoad = if (isMultiPane) SharedItemHolder.selectedItem.value else SharedItemHolder.selectedItem.value
        if (itemToLoad != null) {
            viewModel.setSelectedItem(itemToLoad)
        }
    }

    val deleteState by viewModel.deleteState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(deleteState) {
        when (val s = deleteState) {
            is DeleteState.Success -> {
                RefreshEventAfterDelOrUpdate.requestRefresh()
                viewModel.resetDeleteState()
                SharedItemHolder.clearItem()
                onDeleted()
            }
            is DeleteState.Error -> {
                snackbarHostState.showSnackbar(s.message)
                viewModel.resetDeleteState()
            }
            else -> {}
        }
    }

    val updateState by viewModel.updateState.collectAsState()
    var showUpdateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(updateState) {
        when (val s = updateState) {
            is UpdateState.Success -> {
                // Mostrar snackbar con mensaje del servidor
                snackbarHostState.showSnackbar(s.message)
                // Refrescar la lista de categorías
                RefreshEventAfterDelOrUpdate.requestRefresh()
                // En pantalla estrecha, volver atrás con lista actualizada
                // En pantalla ancha permanecemos en DetailScreen
                if (!isMultiPane) {
                    onUpdated()
                }
                viewModel.resetUpdateState()
            }
            is UpdateState.Error -> {
                snackbarHostState.showSnackbar(s.message)
                viewModel.resetUpdateState()
            }
            else -> {}
        }
    }

    // Diálogo de confirmación para eliminación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirmar eliminación ${itemTextToDeleteOrUpdate(categoryId)}") },
            text = { Text("¿Seguro de que desea eliminar?") },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    // Aquí se llamaría a la función de eliminación del ViewModel, dependiendo de la categoria, pasando el id del item a eliminar
                    when (categoryId) {
                        1 -> state.centro?.idCentro?.let { viewModel.deleteCentro(it) }
                        2 -> state.proveedor?.idProveedor?.let { viewModel.deleteProveedor(it) }
                        3 -> state.servicio?.idServicioTecnico?.let { viewModel.deleteServicioTecnico(it) }
                        4 -> state.asignacion?.idAsignacion?.let { viewModel.deleteAsignacion(it) }
                        5, 8 -> state.usuario?.dni?.let { viewModel.deleteUsuario(it) }
                        6 -> state.dispositivo?.idDispositivo?.let { viewModel.deleteDispositivo(it) }
                        7, 10 -> state.incidencia?.idIncidencia?.let { viewModel.deleteIncidencia(it) }
                    }
                }) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo de confirmación para actualización
    if (showUpdateDialog) {
        AlertDialog(
            onDismissRequest = { showUpdateDialog = false },
            title = { Text("Confirmar actualización ${itemTextToDeleteOrUpdate(categoryId)}") },
            text = { Text("¿Seguro de que desea actualizar?") },
            confirmButton = {
                TextButton(onClick = {
                    showUpdateDialog = false
                    // Aquí se llamaría a la función de actualización del ViewModel, dependiendo de la categoria, pasando el id del item a actualizar
                    when (categoryId) {
                        1 -> {
                            state.centro?.let { viewModel.updateCentro(centroResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                        2 -> {
                            state.proveedor?.let { viewModel.updateProveedor(proveedorResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                        3 -> {
                            state.servicio?.let { viewModel.updateServicioTecnico(servicioTecnicoResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                        4 -> {
                            state.asignacion?.let { viewModel.updateAsignacion(asignacionResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                        5, 8 -> {
                            state.usuario?.let { viewModel.updateUsuario(usuarioResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                        6, 9 -> {
                            state.dispositivo?.let {
                                val currentDate = getCurrentDate()
                                viewModel.updateDispositivo(dispositivoResponseToModel(it).copy(ultimaActualizacion = currentDate))
                            }
                            viewModel.saveCurrentEdited()
                        }
                        7, 10 -> {
                            state.incidencia?.let { viewModel.updateIncidencia(incidenciaResponseToModel(it)) }
                            viewModel.saveCurrentEdited()
                        }
                    }

                }) {
                    Text("Actualizar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showUpdateDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }



    Screen {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->
                    Snackbar(snackbarData = data)
                }
            }
        ) { padding ->
            // Imagen superior con el logo del dispositivo a la categoría
            // Dependiendo de la categoría del item se mostrará una vista u otra
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
            ) {
                // Header con imagen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Selección del icono de la categoría o del tipo de dispositivo

                    println("LOG - Detail CategoryId: $categoryId")
                    val icon: ImageVector =
                        if ((categoryId == 6 || categoryId == 9) && effectiveItem is DispositivoResponseModel) {
                            println("LOG - Detail Dispositivo Model: ${effectiveItem.categoria}")
                            DeviceIconMapper.getIcon(effectiveItem.categoria)
                        } else {
                            CategoryIconMapper.getIcon(categoryId)
                        }

                    Icon(
                        imageVector = icon,
                        contentDescription = "Detalles",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    // Botón redondo de retroceso, solo visible en pantalla completa
                    if (!isMultiPane) {
                        IconButton(
                            onClick = { onBackClick() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp)
                                .size(48.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Volver",
                                    modifier = Modifier.padding(8.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
                // Mostrar detalles según categoría
                key(currentVersion) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        val displayItem = if (state.selectedItem != null) state.selectedItem else effectiveItem
                        when (categoryId) {
                            1 -> (displayItem as? CentroResponseModel)?.let { CentroDetails(it, viewModel) }
                            2 -> (displayItem as? ProveedorResponseModel)?.let { ProveedorDetails(it, viewModel) }
                            3 -> (displayItem as? ServicioTecnicoResponseModel)?.let { ServicioTecnicoDetails(it, viewModel) }
                            4 -> (displayItem as? AsignacionResponseModel)?.let { AsignacionDetails(it, viewModel) }
                            5, 8 -> (displayItem as? UsuarioResponseModel)?.let { UsuarioDetails(it, categoryId, viewModel) }
                            6, 9 -> (displayItem as? DispositivoResponseModel)?.let {
                                DispositivoDetails(
                                    dispositivo = it,
                                    categoryId = categoryId,
                                    viewModel = viewModel,
                                    onCreateIncidencia = { categoryId, idDispositivo, idCentro ->
                                        onCreateIncidencia(categoryId, idDispositivo, idCentro)
                                    }
                                )
                            }
                            7, 10 -> (displayItem as? IncidenciaResponseModel)?.let { IncidenciaDetails(it, viewModel, categoryId) }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(8.dp))

                // Botones para actualizar y eliminar
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (categoryId != 9) {
                        // La opción eliminar no está disponible para Dire y Resp en dispositivos
                        Button(
                            onClick = { showDeleteDialog = true },
                            modifier = Modifier.weight(1f),
                            enabled = deleteState !is DeleteState.Loading,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            )
                        ) {
                            if (deleteState is DeleteState.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    color = MaterialTheme.colorScheme.onError,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text("Eliminar")
                            }
                        }
                    }

                    Button(
                        onClick = { showUpdateDialog = true},
                        modifier = Modifier.weight(1f),
                        enabled = updateState !is UpdateState.Loading
                    ) {
                        if (updateState is UpdateState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Actualizar")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

            }


        }
    }
}

fun incidenciaResponseToModel(it: IncidenciaResponseModel): IncidenciaModel {
    return IncidenciaModel(
        idIncidencia = it.idIncidencia,
        idCentro = it.idCentro,
        idDispositivo = it.idDispositivo,
        idServicioTecnico = it.idServicioTecnico,
        dniResponsable = it.dniResponsable,
        descripcion = it.descripcion,
        fechaReporte = it.fechaReporte,
        fechaCierre = it.fechaCierre,
        estado = it.estado
    )
}

fun dispositivoResponseToModel(it: DispositivoResponseModel): DispositivoModel {
    return DispositivoModel(
        idDispositivo = it.idDispositivo,
        idCentro = it.idCentro,
        nombre = it.nombre,
        numSerie = it.numSerie,
        marcaModelo = it.marcaModelo,
        ultimaActualizacion = it.ultimaActualizacion,
        observaciones = it.observaciones,
        estado = it.estado,
        ubicacion = it.ubicacion,
        uso = it.uso,
        categoria = it.categoria,
        idAsignacion = it.idAsignacion
    )
}

fun usuarioResponseToModel(it: UsuarioResponseModel): UsuarioModel {
    return UsuarioModel(
        idUsuario = it.idUsuario,
        dni = it.dni,
        idCentro = it.idCentro,
        nombre = it.nombre,
        apellidos = it.apellidos,
        email = it.email,
        departamento = it.departamento,
        rol = it.rol,
        passwdHash = "null"
    )
}

fun asignacionResponseToModel(it: AsignacionResponseModel): AsignacionModel {
    return AsignacionModel(
        idAsignacion = it.idAsignacion,
        idProveedor = it.idProveedor,
        idCentro = it.idCentro,
        entrega = it.entrega
    )
}

fun servicioTecnicoResponseToModel(it: ServicioTecnicoResponseModel): ServicioTecnicoModel {
    return ServicioTecnicoModel(
        idServicioTecnico = it.idServicioTecnico,
        nombre = it.nombre,
        direccion = it.direccion,
        telefono = it.telefono,
        email = it.email
    )
}

fun proveedorResponseToModel(it: ProveedorResponseModel): ProveedorModel {
    return ProveedorModel(
        idProveedor = it.idProveedor,
        nombre = it.nombre,
        direccion = it.direccion,
        telefono = it.telefono,
        email = it.email
    )
}

fun centroResponseToModel(it: CentroResponseModel): CentroModel {
    return CentroModel(
        idCentro = it.idCentro,
        tipo = it.tipo,
        nombre = it.nombre,
        direccion = it.direccion,
        municipio = it.municipio
    )
}

fun itemTextToDeleteOrUpdate(categoryId: Int): String {
    return when (categoryId) {
        1 -> "centro"
        2 -> "proveedor"
        3 -> "servicio técnico"
        4 -> "asignación"
        5, 8 -> "usuario"
        6, 9 -> "dispositivo"
        7, 10 -> "incidencia"
        else -> "entrada"
    }
}

