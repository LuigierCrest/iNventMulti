package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.IncidenciaResponseModel

@Composable
fun IncidenciaDetails (incidencia: IncidenciaResponseModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de incidencia: ${incidencia.idIncidencia}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de centro: ${incidencia.idCentro}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de dispositivo: ${incidencia.idDispositivo}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de servicio técnico: ${incidencia.idServicioTecnico}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "DNI del responsable: ${incidencia.dniResponsable}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Descripción: ${incidencia.descripcion?: "Sin descripción"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Fecha de reporte: ${incidencia.fechaReporte}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Fecha de cierre: ${incidencia.fechaCierre?: "No cerrada"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Estado: ${incidencia.estado}",
            style = MaterialTheme.typography.titleMedium
        )
    }
}