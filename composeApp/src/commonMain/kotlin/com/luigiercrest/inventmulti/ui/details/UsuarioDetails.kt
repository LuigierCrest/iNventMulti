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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.UsuarioResponseModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioDetails(usuario: UsuarioResponseModel, categoryId: Int, modifier: Modifier = Modifier) {

    // Categoría 5 administradores
    // Categoría 8 para Dire

    var dni by remember { mutableStateOf(usuario.dni?:"") }
    var idCentro by remember { mutableStateOf(usuario.idCentro.toString()?:"") }
    var nombre by remember { mutableStateOf(usuario.nombre?:"") }
    var apellidos by remember { mutableStateOf(usuario.apellidos?:"") }
    var email by remember { mutableStateOf(usuario.email?:"") }
    var departamento by remember { mutableStateOf(usuario.departamento?:"") }
    var rol by remember { mutableStateOf(usuario.rol?:"") }
    var rolExpanded by remember { mutableStateOf(false) }

    val rolOptions = when (categoryId) {
        5 -> listOf("ADMIN", "DIRE", "RESP")
        8 -> listOf("DIRE", "RESP")
        else -> emptyList()
    }

    Column (modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = usuario.idUsuario.toString(),
            onValueChange = {},
            label = { Text("Nº de usuario") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI/NIE") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idCentro,
            onValueChange = { idCentro = it },
            label = { Text("Nº de centro") },
            readOnly = (categoryId == 8), // true para 8 dire
            enabled = (categoryId != 8), // false para 8 dire
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
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = departamento,
            onValueChange = { departamento = it },
            label = { Text("Departamento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))

        // Dropdown de Rol según categoría
        ExposedDropdownMenuBox(
            expanded = rolExpanded,
            onExpandedChange = { rolExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = rol,
                onValueChange = {},
                label = { Text("Rol") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = rolExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
            )
            ExposedDropdownMenu(
                expanded = rolExpanded,
                onDismissRequest = { rolExpanded = false }
            ) {
                rolOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            rol = option
                            rolExpanded = false
                        }
                    )
                }
            }
        }
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
