package com.luigiercrest.inventmulti.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.inventmulti.ui.details.AsignacionDetails
import com.luigiercrest.inventmulti.ui.details.CentroDetails
import com.luigiercrest.inventmulti.ui.details.DispositivoDetails
import com.luigiercrest.inventmulti.ui.details.IncidenciaDetails
import com.luigiercrest.inventmulti.ui.details.ProveedorDetails
import com.luigiercrest.inventmulti.ui.details.ServicioTecnicoDetails
import com.luigiercrest.inventmulti.ui.details.UsuarioDetails
import com.luigiercrest.inventmulti.utils.CategoryIconMapper
import com.luigiercrest.inventmulti.utils.DeviceIconMapper
import com.luigiercrest.presentation.detail.DetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    categoryId: Int,
    item: Any?,
    isMultiPane: Boolean = false,
    viewModel: DetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    // Carga los detalles al cambiar el item
    LaunchedEffect(item) {
        viewModel.setSelectedItem(item)
    }

    Screen {
        Scaffold(
            modifier = modifier.fillMaxSize()
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
                        .height(200.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Selección del icono de la categoría o del tipo de dispositivo

                    println("LOG - Detail CategoryId: $categoryId")
                    val icon: ImageVector =
                        if ((categoryId == 6 || categoryId == 9) && item is DispositivoResponseModel) {
                            println("LOG - Detail Dispositivo Model: ${item.categoria}")
                            DeviceIconMapper.getIcon(item.categoria)
                        } else {
                            CategoryIconMapper.getIcon(categoryId)
                        }

                    Icon(
                        imageVector = icon,
                        contentDescription = "Detalles",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.size(16.dp))


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
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    when (categoryId) {
                        1 -> state.centro?.let { CentroDetails(it) }
                        2 -> state.proveedor?.let { ProveedorDetails(it) }
                        3 -> state.servicio?.let { ServicioTecnicoDetails(it) }
                        4 -> state.asignacion?.let { AsignacionDetails(it) }
                        5, 8 -> state.usuario?.let { UsuarioDetails(it) }
                        6, 9 -> state.dispositivo?.let { DispositivoDetails(it) }
                        7, 10 -> state.incidencia?.let { IncidenciaDetails(it) }
                    }
                }
            }


        }
    }


}
//
//@Composable
//private fun getTitleFromItem(item: Any?): String = when (item) {
//    is CentroResponseModel -> "${item.tipo} ${item.nombre}"
//    is ProveedorResponseModel -> item.nombre ?: "Proveedor"
//    is ServicioTecnicoResponseModel -> item.nombre ?: "Servicio Técnico"
//    is AsignacionResponseModel -> "Asignación ${item.idAsignacion}"
//    is UsuarioResponseModel -> "${item.nombre} ${item.apellidos}"
//    is DispositivoResponseModel -> "Dispositivo ${item.idDispositivo}"
//    is IncidenciaResponseModel -> "Incidencia ${item.idIncidencia}"
//    else -> "Detalles"
//}
