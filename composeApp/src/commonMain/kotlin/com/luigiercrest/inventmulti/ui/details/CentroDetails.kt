package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.CentroResponseModel

@Composable
fun CentroDetails (centro: CentroResponseModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de centro: ${centro.idCentro}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Tipo de centro: ${centro.tipo}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nombre: ${centro.nombre}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Dirección: ${centro.direccion ?: "Sin dirección"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Municipio: ${centro.municipio ?: "Sin municipio"}",
            style = MaterialTheme.typography.titleMedium
        )}

}