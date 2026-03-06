package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.presentation.detail.DetailViewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncidenciaDetails (
    incidencia: IncidenciaResponseModel,
    viewModel: DetailViewModel,
    categoryId: Int,
    modifier: Modifier = Modifier) {

    var idCentro by remember(incidencia.idCentro) { mutableStateOf(incidencia.idCentro?.toString() ?: "") }
    var idDispositivo by remember(incidencia.idDispositivo) { mutableStateOf(incidencia.idDispositivo?.toString() ?: "") }
    var idServicioTecnico by remember(incidencia.idServicioTecnico) { mutableStateOf(incidencia.idServicioTecnico?.toString() ?: "") }
    var dniResponsable by remember(incidencia.dniResponsable) { mutableStateOf(incidencia.dniResponsable ?: "") }
    var descripcion by remember(incidencia.descripcion) { mutableStateOf(incidencia.descripcion ?: "") }
    var fechaReporte by remember(incidencia.fechaReporte) { mutableStateOf(incidencia.fechaReporte ?: "") }
    var fechaCierre by remember(incidencia.fechaCierre) { mutableStateOf(incidencia.fechaCierre ?: "") }
    var estado by remember(incidencia.estado) { mutableStateOf(incidencia.estado ?: "") }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    var estadoExpanded by remember { mutableStateOf(false) }
    val estadoOpciones = listOf("Averiado", "En Servicio Técnico", "Reparado", "Faltan consumibles")


    LaunchedEffect(idCentro, idDispositivo, idServicioTecnico, dniResponsable, descripcion, fechaReporte, fechaCierre, estado) {
        viewModel.setIncidencia(
            incidencia.copy(
                idCentro = idCentro.toIntOrNull(),
                idDispositivo = idDispositivo.toIntOrNull(),
                idServicioTecnico = idServicioTecnico.toIntOrNull(),
                dniResponsable = dniResponsable,
                descripcion = descripcion,
                fechaReporte = fechaReporte,
                fechaCierre = fechaCierre,
                estado = estado
            )
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val localDate = Instant.fromEpochMilliseconds(millis)
                            .toLocalDateTime(TimeZone.UTC)
                            .date
                        fechaCierre = localDate.toString() // Formato yyyy-MM-dd
                    }
                    showDatePicker = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = incidencia.idIncidencia.toString(),
            onValueChange = {},
            label = { Text("Nº de incidencia") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idCentro,
            onValueChange = { idCentro = it },
            label = { Text("Nº de centro") },
            readOnly = (categoryId == 10), // para categoría 10 true
            enabled = (categoryId != 10), // para categoría 10 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idDispositivo,
            onValueChange = { idDispositivo = it },
            label = { Text("Nº de dispositivo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idServicioTecnico,
            onValueChange = { idServicioTecnico = it },
            label = { Text("Nº de servicio técnico") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = dniResponsable,
            onValueChange = { dniResponsable = it },
            label = { Text("DNI del responsable") },
            readOnly = (categoryId == 10), // para categoría 10 true
            enabled = (categoryId != 10), // para categoría 10 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = fechaReporte,
            onValueChange = { fechaReporte = it },
            label = { Text("Fecha de reporte") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = fechaCierre,
            onValueChange = { fechaCierre = it },
            label = { Text("Fecha de cierre") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {showDatePicker = true}) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        ExposedDropdownMenuBox(
            expanded = estadoExpanded,
            onExpandedChange = { estadoExpanded = !estadoExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = estado,
                onValueChange = {},
                readOnly = true,
                label = { Text("Estado") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estadoExpanded) },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = estadoExpanded,
                onDismissRequest = { estadoExpanded = false }
            ) {
                estadoOpciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            estado = opcion
                            estadoExpanded = false
                        }
                    )
                }
            }
        }

    }
}