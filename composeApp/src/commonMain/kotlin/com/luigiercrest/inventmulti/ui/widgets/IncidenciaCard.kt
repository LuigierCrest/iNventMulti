package com.luigiercrest.inventmulti.ui.widgets

//import androidx.compose.foundation.LocalIndication
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.inventmulti.utils.CategoryIconMapper

@Composable
fun IncidenciaCard(
    incidencia: IncidenciaResponseModel,
    categoryId: Int = 7,
    //onIncidenciaClick: (IncidenciaDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clickable(
//                onClick = {onIncidenciaClick(incidencia) },
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
                    contentDescription = "Incidencia",
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
                    text = incidencia.idDispositivo.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Inicio: " + incidencia.fechaReporte,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = incidencia.estado ?: "Sin estado",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }


}
