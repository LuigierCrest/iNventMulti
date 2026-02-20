package com.luigiercrest.inventmulti.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.inventmulti.utils.CategoryIconMapper

@Composable
fun UsuarioCard(
    usuario: UsuarioResponseModel,
    categoryId: Int = 5,
//    onUsuarioClick: (UsuarioDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clickable(
//                onClick = {onUsuarioClick(usuario) },
//                interactionSource = remember { MutableInteractionSource() },
//                indication = LocalIndication.current
//            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Surface(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Icon(
                    imageVector = CategoryIconMapper.getIcon(categoryId),
                    contentDescription = "Usuario",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.size(12.dp))
            Column {
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = usuario.nombre+ " " + usuario.apellidos,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = usuario.dni ?: "Sin DNI",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = usuario.idCentro.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = usuario.rol ?: "Sin rol",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }


}
