package com.luigiercrest.inventmulti.ui.widgets

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.data.dto.ServicioTecnicoDTO
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.inventmulti.utils.CategoryIconMapper

@Composable
fun ServicioTecnicoCard(
    servicioTecnico: ServicioTecnicoResponseModel,
    categoryId: Int = 3,
//    onServicioTecnicoClick: (ServicioTecnicoDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clickable(
//                onClick = {onServicioTecnicoClick(servicioTecnico) },
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
                    contentDescription = "ServTécnico",
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
                    text = servicioTecnico.nombre ?: "Sin nombre",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = servicioTecnico.direccion ?: "Sin dirección",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }


}
