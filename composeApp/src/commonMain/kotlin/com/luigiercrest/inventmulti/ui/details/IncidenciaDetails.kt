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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.IncidenciaResponseModel

@Composable
fun IncidenciaDetails (incidencia: IncidenciaResponseModel, categoryId: Int, modifier: Modifier = Modifier) {

    var idCentro by remember { mutableStateOf(incidencia.idCentro?.toString() ?: "") }
    var idDispositivo by remember { mutableStateOf(incidencia.idDispositivo?.toString() ?: "") }
    var idServicioTecnico by remember { mutableStateOf(incidencia.idServicioTecnico?.toString() ?: "") }
    var dniResponsable by remember { mutableStateOf(incidencia.dniResponsable ?: "") }
    var descripcion by remember { mutableStateOf(incidencia.descripcion ?: "") }
    var fechaReporte by remember { mutableStateOf(incidencia.fechaReporte ?: "") }
    var fechaCierre by remember { mutableStateOf(incidencia.fechaCierre ?: "") }
    var estado by remember { mutableStateOf(incidencia.estado ?: "") }

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
            readOnly = (categoryId == 10), // para categoría 10 true
            enabled = (categoryId != 10), // para categoría 10 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = fechaCierre,
            onValueChange = { fechaCierre = it },
            label = { Text("Fecha de cierre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text("Estado") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        // Botones para actualizar y eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (categoryId == 7) {
                Button(
                    onClick = { /* TODO: lógica de borrar */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Eliminar")
                }
            }

            Button(
                onClick = { /* TODO: lógica de actualizar */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Actualizar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}