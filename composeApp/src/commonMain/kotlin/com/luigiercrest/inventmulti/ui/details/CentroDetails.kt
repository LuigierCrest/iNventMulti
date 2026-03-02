package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.presentation.detail.DetailViewModel

@Composable
fun CentroDetails (
    centro: CentroResponseModel,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {

    var tipo by remember(centro.tipo) { mutableStateOf(centro.tipo?:"") }
    var nombre by remember(centro.nombre) { mutableStateOf(centro.nombre?:"") }
    var direccion by remember(centro.direccion) { mutableStateOf(centro.direccion?:"") }
    var municipio by remember(centro.municipio) { mutableStateOf(centro.municipio?:"") }

    LaunchedEffect(tipo, nombre, direccion, municipio) {
        viewModel.setCentro(
            centro.copy(
                tipo = tipo,
                nombre = nombre,
                direccion = direccion,
                municipio = municipio
            )
        )
    }

    Column (modifier = modifier.fillMaxWidth()) {
        // Número de centro - solo lectura
        OutlinedTextField(
            value = centro.idCentro.toString(),
            onValueChange = {},
            label = { Text("Nº de centro") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de centro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = municipio,
            onValueChange = { municipio = it },
            label = { Text("Municipio") },
            modifier = Modifier.fillMaxWidth()
        )

    }

}