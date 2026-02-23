package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.presentation.detail.DeleteState
import com.luigiercrest.presentation.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioDetails(
    usuario: UsuarioResponseModel,
    categoryId: Int,
    viewModel: DetailViewModel,
    onDeleted: () -> Unit={},
    modifier: Modifier = Modifier) {

    val deleteState by viewModel.deleteState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Resetea el estado de eliminación al entrar en un nuevo usuario
    LaunchedEffect(usuario.idUsuario) {
        viewModel.resetDeleteState()
    }

    // Navegar tras eliminación exitosa
    LaunchedEffect(deleteState) {
        if (deleteState is DeleteState.Success) {
            onDeleted()
            viewModel.resetDeleteState()
        }
    }

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

    // Diálogo de confirmación de eliminación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar usuario") },
            text = { Text("¿Estás seguro de que quieres eliminar al usuario ${usuario.nombre} ${usuario.apellidos}?") },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    usuario.dni?.let { viewModel.deleteUsuario(it) }
                }) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
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
        // Mostrar error si existe
        if (deleteState is DeleteState.Error) {
            Text(
                text = (deleteState as DeleteState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Botones para actualizar y eliminar
        if (deleteState !is DeleteState.Loading) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = { showDeleteDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Eliminar")
                }
                Button(
                    onClick = { /* TODO: lógica de guardar cambios */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Actualizar")
                }
            }
        } else {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

}
