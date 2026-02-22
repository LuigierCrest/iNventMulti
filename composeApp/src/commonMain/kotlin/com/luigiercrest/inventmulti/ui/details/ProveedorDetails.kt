package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.ProveedorResponseModel

@Composable
fun ProveedorDetails (proveedor: ProveedorResponseModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de proveedor: ${proveedor.idProveedor}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nombre: ${proveedor.nombre}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Dirección: ${proveedor.direccion?: "Sin dirección"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Teléfono: ${proveedor.telefono?: "Sin teléfono"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Email: ${proveedor.email?: "Sin email"}",
            style = MaterialTheme.typography.titleMedium
        )
    }

}