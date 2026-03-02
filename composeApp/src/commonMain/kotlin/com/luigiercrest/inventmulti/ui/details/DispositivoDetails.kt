package com.luigiercrest.inventmulti.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.presentation.detail.DeleteState
import com.luigiercrest.presentation.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispositivoDetails (
    dispositivo: DispositivoResponseModel,
    categoryId: Int,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {

    // Categoría 6 administradores
    // Categoría 9 para Dire y Resp

    var idCentro by remember(dispositivo.idCentro) { mutableStateOf(dispositivo.idCentro?.toString() ?: "") }
    var nombre by remember(dispositivo.nombre) { mutableStateOf(dispositivo.nombre ?: "") }
    var numSerie by remember(dispositivo.numSerie) { mutableStateOf(dispositivo.numSerie ?: "") }
    var marcaModelo by remember(dispositivo.marcaModelo) { mutableStateOf(dispositivo.marcaModelo ?: "") }
    var ultimaActualizacion by remember(dispositivo.ultimaActualizacion) { mutableStateOf(dispositivo.ultimaActualizacion ?: "") }
    var observaciones by remember(dispositivo.observaciones) { mutableStateOf(dispositivo.observaciones ?: "") }
    var categoria by remember(dispositivo.categoria) { mutableStateOf(dispositivo.categoria ?: "") }
    var estado by remember(dispositivo.estado) { mutableStateOf(dispositivo.estado ?: "") }
    var uso by remember(dispositivo.uso) { mutableStateOf(dispositivo.uso ?: "") }
    var ubicacion by remember(dispositivo.ubicacion) { mutableStateOf(dispositivo.ubicacion ?: "") }
    var idAsignacion by remember(dispositivo.idAsignacion) { mutableStateOf(dispositivo.idAsignacion?.toString() ?: "") }

    // Estados de expansión para los dropdowns
    var categoriaExpanded by remember { mutableStateOf(false) }
    var estadoExpanded by remember { mutableStateOf(false) }
    var usoExpanded by remember { mutableStateOf(false) }

    // Cierra los dropdowns excepto el activo
    fun collapseAllExcept(target: String) {
        if (target != "categoria") categoriaExpanded = false
        if (target != "estado") estadoExpanded = false
        if (target != "uso") usoExpanded = false
    }

    // Opciones de cada dropdown
    val usoOptions = remember {listOf("","Docente", "Alumnado", "Administración", "No definido")}
    val estadoOptions = remember {listOf( "",
        "Destruido - enviado a reciclar",
        "En Almacén",
        "No Localizable",
        "No Operativo",
        "Operativo",
        "Pendiente Finalizar Enajenación",
        "Recibido, no instalado")}
    val categoriaOptions = remember { listOf( "",
        "Audiovisuales", "Carros", "Disco duro", "Escáner", "Impresora",
        "Miniportátil", "Monitor", "Ordenador", "Otros", "PDI/PIM",
        "Portátil", "Proyector", "Redes", "Robótica", "SAI/UPS",
        "Tablet", "Webcam" )}

    // Sicronizar cambios con el ViewModel cada vez que cambia un campo
    LaunchedEffect(idCentro, nombre, numSerie, marcaModelo, ultimaActualizacion, observaciones, categoria, estado, uso, ubicacion, idAsignacion) {
        viewModel.setDispositivo(
            dispositivo.copy(
                idCentro = idCentro.toIntOrNull(),
                nombre = nombre,
                numSerie = numSerie,
                marcaModelo = marcaModelo,
                ultimaActualizacion = ultimaActualizacion,
                observaciones = observaciones,
                categoria = categoria,
                estado = estado,
                uso = uso,
                ubicacion = ubicacion,
                idAsignacion = idAsignacion.toIntOrNull()
            )
        )
    }

    Column {
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* TODO: lógica de crear incidencia */ },
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = "Incidencia",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text("Incidencia")
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = dispositivo.idDispositivo.toString(),
            onValueChange = {},
            label = { Text("Nº de dispositivo") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))

        OutlinedTextField(
            value = idCentro,
            onValueChange = { idCentro = it },
            label = { Text("Nº de centro") },
            readOnly = (categoryId == 9), // para categoría 9 true
            enabled = (categoryId != 9), // para categoría 9 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            readOnly = (categoryId == 9), // para categoría 9 true,
            enabled = (categoryId != 9), // para categoría 9 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = numSerie,
            onValueChange = { numSerie = it },
            label = { Text("Nº de serie") },
            readOnly = (categoryId == 9), // para categoría 9 true,
            enabled = (categoryId != 9), // para categoría 9 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = marcaModelo,
            onValueChange = { marcaModelo = it },
            label = { Text("Marca/Modelo") },
            readOnly = (categoryId == 9), // para categoría 9 true,
            enabled = (categoryId != 9), // para categoría 9 false
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = ultimaActualizacion,
            onValueChange = { ultimaActualizacion = it },
            label = { Text("Última actualización") },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = observaciones,
            onValueChange = { observaciones = it },
            label = { Text("Observaciones") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))

        // Dropdown Categoría
        ExposedDropdownMenuBox(
            expanded = categoriaExpanded,
            onExpandedChange = {
                collapseAllExcept("categoria")
                categoriaExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                label = { Text("Categoría") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoriaExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
            )
            ExposedDropdownMenu(
                expanded = categoriaExpanded,
                onDismissRequest = { categoriaExpanded = false }
            ) {
                categoriaOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            categoria = option
                            categoriaExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        // Dropdown de Estado
        ExposedDropdownMenuBox(
            expanded = estadoExpanded,
            onExpandedChange = {
                collapseAllExcept("estado")
                estadoExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = estado,
                onValueChange = {},
                label = { Text("Estado") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estadoExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
            )
            ExposedDropdownMenu(
                expanded = estadoExpanded,
                onDismissRequest = { estadoExpanded = false }
            ) {
                estadoOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            estado = option
                            estadoExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        // Dropdown de Uso
        ExposedDropdownMenuBox(
            expanded = usoExpanded,
            onExpandedChange = {
                collapseAllExcept("uso")
                usoExpanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uso,
                onValueChange = {},
                label = { Text("Uso") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = usoExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
            )
            ExposedDropdownMenu(
                expanded = usoExpanded,
                onDismissRequest = { usoExpanded = false }
            ) {
                usoOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            uso = option
                            usoExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = idAsignacion,
            onValueChange = { idAsignacion = it },
            label = { Text("Nº de asignación") },
            readOnly = (categoryId == 9), // para categoría 9 true,
            enabled = (categoryId != 9), // para categoría 9 false
            modifier = Modifier.fillMaxWidth()
        )

    }

}