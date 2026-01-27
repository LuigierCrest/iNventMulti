package com.luigiercrest.inventmulti

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import inventmulti.composeapp.generated.resources.Res
import inventmulti.composeapp.generated.resources.iNvent_logo_wellcome
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.luigiercrest.presentation.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel




@Composable
@Preview (showBackground = true)
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    val dni = viewModel.dni.collectAsState()
    val passwd = viewModel.password.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.value.login?.let{
                Text(it.toString())
            }
            Image(painterResource(Res.drawable.iNvent_logo_wellcome), null, modifier = Modifier.size(300.dp))
            Text("iNvent", style = MaterialTheme.typography.headlineLarge)
            Text ("Una App para reunirlo todo")
            Spacer(modifier = Modifier.size(32.dp))
            OutlinedTextField(
               dni.value, onValueChange = { viewModel.onDniChanged(it) }
            )
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                passwd.value,
                onValueChange = {viewModel.onPasswordChanged(it)},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Image(
                        imageVector = if (!passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (!passwordVisible) "Mostrar contraseña" else "Ocultar contraseña",
                        modifier = Modifier.size(36.dp).clickable{
                            passwordVisible = !passwordVisible
                        }
                    )
                }
            )

            Spacer(modifier = Modifier.size(4.dp))

            AnimatedVisibility(state.value.isLoading) {
                CircularProgressIndicator()
            }

            Button(
                onClick = {checkUserAndPassword(dni.value, passwd.value, viewModel)},
                enabled = !state.value.isLoading
            ) {
                Text(text = "Comenzar")
            }
            Spacer(modifier = Modifier.size(4.dp))
            state.value.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

    }
}



private fun checkUserAndPassword(dni: String, passwd: String, viewModel: LoginViewModel)  {

    if (dni.isEmpty()) {
        // Comprueba que el DNI/NIE no esté vacío
        println("El DNI/NIE no puede estar vacío")
        // comprobar si el DNI/NIE tiene el formato correcto
    } else if (passwd.isEmpty()) {
        // Comprueba que la contraseña no esté vacía
        println("La contraseña no puede estar vacía")
    } else {
        // Mandar a la API para verificar las credenciales
        // Si es correcto, proceder al siguiente paso
        viewModel.comenzar()
        // Cargar las tareas del rol
        //TareasScreen()
        // Si no, mostrará un mensaje de error
    }
}


