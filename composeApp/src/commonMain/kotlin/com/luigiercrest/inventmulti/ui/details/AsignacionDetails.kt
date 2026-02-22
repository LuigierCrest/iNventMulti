package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.AsignacionResponseModel

@Composable
fun AsignacionDetails(
    asignacion: AsignacionResponseModel,
    modifier: Modifier = Modifier
) {

    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de asignación: ${asignacion.idAsignacion}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Proveedor: ${asignacion.idProveedor}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text ="Centro: ${asignacion.idCentro}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Fecha de entrega: ${asignacion.entrega?: "Sin entrega"}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}