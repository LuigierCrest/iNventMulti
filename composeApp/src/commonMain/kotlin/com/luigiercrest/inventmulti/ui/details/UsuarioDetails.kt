package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.UsuarioResponseModel

@Composable
fun UsuarioDetails(usuario: UsuarioResponseModel, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de usuario: ${usuario.idUsuario}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "DNI/NIE: ${usuario.dni}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nª de centro: ${usuario.idCentro}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Nombre: ${usuario.nombre}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Apellidos: ${usuario.apellidos}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Email: ${usuario.email?: "Sin email"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Departamento: ${usuario.departamento?: "Sin departamento"}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Rol: ${usuario.rol}",
            style = MaterialTheme.typography.titleMedium
        )
        //passwd_hash
    }

}
