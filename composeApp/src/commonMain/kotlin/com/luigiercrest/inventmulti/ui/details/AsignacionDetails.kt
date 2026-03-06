package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.presentation.detail.DetailViewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignacionDetails(
    asignacion: AsignacionResponseModel,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    var idProveedor by remember(asignacion.idProveedor) { mutableStateOf(asignacion.idProveedor?.toString() ?: "") }
    var idCentro by remember(asignacion.idCentro) { mutableStateOf(asignacion.idCentro?.toString() ?: "") }
    var entrega by remember(asignacion.entrega) { mutableStateOf(asignacion.entrega ?: "") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(idProveedor, idCentro, entrega) {
        viewModel.setAsignacion(
            asignacion.copy(
                idProveedor = idProveedor.toIntOrNull(),
                idCentro = idCentro.toIntOrNull(),
                entrega = entrega
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
                        entrega = localDate.toString() // yyyy-MM-dd
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
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = asignacion.idAsignacion.toString(),
            onValueChange = {},
            label = { Text("Nº de asignación") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idProveedor,
            onValueChange = { idProveedor = it },
            label = { Text("Proveedor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))

        OutlinedTextField(
            value = idCentro,
            onValueChange = { idCentro = it },
            label = { Text("Centro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = entrega,
            onValueChange = {},
            label = { Text("Fecha de entrega") },
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
    }
}


