package com.luigiercrest.inventmulti

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import inventmulti.composeapp.generated.resources.Res
import inventmulti.composeapp.generated.resources.iNvent_logo_wellcome
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoginScreen() {
    var dni by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painterResource(Res.drawable.iNvent_logo_wellcome), null, modifier = Modifier.size(300.dp))
        Text("iNvent", style = MaterialTheme.typography.headlineLarge)
        Text ("Una App para reunirlo todo")
        Spacer(modifier = Modifier.size(32.dp))
        OutlinedTextField(value=dni, onValueChange = {dni=it}, label = { Text("DNI/NIE") })
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(value=passwd, onValueChange = {passwd=it}, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = {
            checkUserAndPassword(dni,passwd)}){Text(text = "Comenzar")
        }
        Spacer(modifier = Modifier.size(4.dp))

    }
}


private fun checkUserAndPassword(dni: String, passwd: String) {
    // comprobar si el DNI/NIE y la contraseña son correctos
    if (dni.isEmpty()) {
        println("El DNI/NIE no puede estar vacío")
    } else if (passwd.isEmpty()) {
        println("La contraseña no puede estar vacía")
    } else {
        println("Inicio de sesión correcto")
    }
}
