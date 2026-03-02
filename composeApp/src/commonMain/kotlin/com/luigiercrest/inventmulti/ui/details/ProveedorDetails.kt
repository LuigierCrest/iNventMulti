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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.presentation.detail.DetailViewModel

@Composable
fun ProveedorDetails (
    proveedor: ProveedorResponseModel,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    var nombre by remember(proveedor.nombre) { mutableStateOf(proveedor.nombre?:"") }
    var direccion by remember(proveedor.direccion) { mutableStateOf(proveedor.direccion?:"") }
    var telefono by remember(proveedor.telefono) { mutableStateOf(proveedor.telefono?:"") }
    var email by remember(proveedor.email) { mutableStateOf(proveedor.email?:"") }

    // Sincronizar cambios con el ViewModel cada vez que cambia un campo
    LaunchedEffect(nombre, direccion, telefono, email) {
        viewModel.setProveedor(
            proveedor.copy(
                nombre = nombre,
                direccion = direccion,
                telefono = telefono,
                email = email
            )
        )
    }



    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = proveedor.idProveedor.toString(),
            onValueChange = {},
            label = { Text("Nº de proveedor") },
            readOnly = true,
            enabled = false,
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
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

    }

}