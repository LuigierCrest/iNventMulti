package com.luigiercrest.inventmulti.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luigiercrest.inventmulti.ui.Screen
import com.luigiercrest.inventmulti.utils.isValidDni
import com.luigiercrest.presentation.create.CreateViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateIncidenciaScreen(
    categoryId: Int,
    idDispositivo: Int,
    idCentro: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateViewModel = koinViewModel()
){
    val uiState by viewModel.state.collectAsState()

    var idDispositivo by remember { mutableStateOf(idDispositivo.toString()) }
    var idCentro by remember { mutableStateOf(idCentro.toString()) }
    var idServicioTecnico by remember { mutableStateOf("") }
    var servicioTecnicoExpanded by remember { mutableStateOf(false) }
    var servicioTecnicoNombre by remember { mutableStateOf("") }
    var dniResponsable by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    var dniError by remember { mutableStateOf<String?>(null) }

    var estadoExpanded by remember { mutableStateOf(false) }
    val estadoOpciones = listOf("Averiado", "En Servicio Técnico", "Reparado", "Faltan consumibles")

    LaunchedEffect(Unit) {
        viewModel.resetState()
        viewModel.loadServicios()
    }

    LaunchedEffect(categoryId) {
        if (categoryId == 9) {
            dniResponsable = viewModel.getCurrentUserDni()
            dniError = null
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onBackClick()
    }

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Nueva incidencia") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            }
        ) { padding ->

            Column(modifier = modifier.fillMaxWidth().padding(padding)) {
                Spacer(modifier = Modifier.size(4.dp))
                OutlinedTextField(
                    value = idDispositivo,
                    onValueChange = { idDispositivo = it },
                    label = { Text("Nº de dispositivo") },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(4.dp))
                OutlinedTextField(
                    value = idCentro,
                    onValueChange = { idCentro = it },
                    label = { Text("Nº de centro") },
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(4.dp))
                ExposedDropdownMenuBox(
                    expanded = servicioTecnicoExpanded,
                    onExpandedChange = { servicioTecnicoExpanded = !servicioTecnicoExpanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = servicioTecnicoNombre,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Servicio técnico") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = servicioTecnicoExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = servicioTecnicoExpanded,
                        onDismissRequest = { servicioTecnicoExpanded = false }
                    ) {
                        uiState.serviciosTecnicos.forEach { servicio ->
                            DropdownMenuItem(
                                text = { Text("${servicio.idServicioTecnico} - ${servicio.nombre}; ${servicio.direccion}") },
                                onClick = {
                                    idServicioTecnico = servicio.idServicioTecnico?.toString() ?: ""
                                    servicioTecnicoNombre = servicio.nombre ?: "Servicio ${servicio.idServicioTecnico}"
                                    servicioTecnicoExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(4.dp))
                OutlinedTextField(
                    value = dniResponsable,
                    onValueChange = { input ->
                        val normalizado = input.trim().uppercase()
                        dniResponsable = normalizado
                        dniError = if (categoryId == 6 && normalizado.isNotBlank() && !isValidDni(normalizado)) {
                            "DNI/NIE inválido"
                        } else null
                    },
                    label = { Text("DNI del responsable") },
                    singleLine = true,
                    readOnly = categoryId == 9,
                    enabled = categoryId != 9,
                    isError = dniError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                if (dniError != null) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = dniError!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
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
                            .menuAnchor()
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

                Button(
                    onClick = {
                        viewModel.createIncidencia(
                            idDispositivo = idDispositivo.toIntOrNull() ?: 0,
                            idCentro = idCentro.toIntOrNull() ?: 0,
                            idServicioTecnico = idServicioTecnico.toIntOrNull() ?: 0,
                            dniResponsable = dniResponsable,
                            descripcion = descripcion,
                            estado = estado
                        )
                    },
                    enabled = !uiState.isLoading
                            && idDispositivo.isNotBlank()
                            && idCentro.isNotBlank()
                            && idServicioTecnico.isNotBlank()
                            && (categoryId != 6 || (isValidDni(dniResponsable) && dniError == null))
                            && descripcion.isNotBlank()
                            && estado.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text("Crear incidencia")
                    }
                }

                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }

        }
    }

}