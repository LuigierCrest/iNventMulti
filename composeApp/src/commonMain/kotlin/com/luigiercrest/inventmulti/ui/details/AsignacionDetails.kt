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
import com.luigiercrest.domain.models.AsignacionResponseModel

@Composable
fun AsignacionDetails(
    asignacion: AsignacionResponseModel,
    modifier: Modifier = Modifier
) {
    var idProveedor by remember { mutableStateOf(asignacion.idProveedor?.toString() ?: "") }
    var idCentro by remember { mutableStateOf(asignacion.idCentro?.toString() ?: "") }
    var entrega by remember { mutableStateOf(asignacion.entrega ?: "") }

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
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = entrega,
            onValueChange = { entrega = it },
            label = { Text("Fecha de entrega") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        // Botones para actualizar y eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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


