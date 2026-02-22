package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel

@Composable
fun ServicioTecnicoDetails (servicioTecnico: ServicioTecnicoResponseModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de proveedor: ${servicioTecnico.idServicioTecnico}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nombre: ${servicioTecnico.nombre}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Dirección: ${servicioTecnico.direccion?: "Sin dirección"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Teléfono: ${servicioTecnico.telefono?: "Sin teléfono"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Email: ${servicioTecnico.email?: "Sin email"}",
            style = MaterialTheme.typography.titleMedium
        )
    }

}