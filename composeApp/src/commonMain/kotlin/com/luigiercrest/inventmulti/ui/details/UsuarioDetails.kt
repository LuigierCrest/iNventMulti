package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.presentation.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioDetails(
    usuario: UsuarioResponseModel,
    categoryId: Int,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier) {

    // Categoría 5 administradores
    // Categoría 8 para Dire

    var dni by remember(usuario.dni) { mutableStateOf(usuario.dni?:"") }
    var idCentro by remember(usuario.idCentro) { mutableStateOf(usuario.idCentro.toString()) }
    var nombre by remember(usuario.nombre) { mutableStateOf(usuario.nombre?:"") }
    var apellidos by remember(usuario.apellidos) { mutableStateOf(usuario.apellidos?:"") }
    var email by remember(usuario.email) { mutableStateOf(usuario.email?:"") }
    var departamento by remember(usuario.departamento) { mutableStateOf(usuario.departamento?:"") }
    var rol by remember(usuario.rol) { mutableStateOf(usuario.rol?:"") }

    var rolExpanded by remember { mutableStateOf(false) }

    val rolOptions = when (categoryId) {
        5 -> listOf("ADMIN", "DIRE", "RESP")
        8 -> listOf("DIRE", "RESP")
        else -> emptyList()
    }

    LaunchedEffect(dni, idCentro, nombre, apellidos, email, departamento, rol) {
        viewModel.setUsuario(
            usuario.copy(
                dni = dni,
                idCentro = idCentro.toIntOrNull(),
                nombre = nombre,
                apellidos = apellidos,
                email = email,
                departamento = departamento,
                rol = rol
            )
        )
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
                    .menuAnchor()
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

    }

}
