package com.luigiercrest.inventmulti.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.luigiercrest.inventmulti.navigation.NavRoutes
import com.luigiercrest.presentation.navigation.AuthNavigation
import inventmulti.composeapp.generated.resources.Res
import inventmulti.composeapp.generated.resources.iNvent_logo_wellcome
import org.jetbrains.compose.resources.painterResource
import com.luigiercrest.presentation.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LoginScreen(
    backStack: NavBackStack<NavKey>,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    val dniState = viewModel.dni.collectAsState()
    val dniValue = dniState.value
    val isDniValid = remember(dniValue) { isValidDni(dniValue) }
    val passwd = viewModel.password.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    val isPasswordError = dniValue.isNotEmpty() && isDniValid && passwd.value.isEmpty()

    LaunchedEffect(true) {
        viewModel.navigationState.collectLatest {
            when (it) {
                is AuthNavigation.ToHome -> {
                    while (backStack.isNotEmpty()) {
                        backStack.removeAt(backStack.lastIndex)
                    }
                    backStack.add(NavRoutes.Home)
                }
                else -> {}
            }

        }
    }

    Screen {
        Scaffold { innerPadding ->

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(innerPadding)
                    .safeContentPadding()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painterResource(Res.drawable.iNvent_logo_wellcome),
                    null,
                    modifier = Modifier.size(300.dp)
                )
                Text("iNvent", style = MaterialTheme.typography.headlineLarge)
                Text("Una App para reunirlo todo")
                Spacer(modifier = Modifier.size(32.dp))
                OutlinedTextField(
                    value = dniValue,
                    onValueChange = {
                        val normalizado = it.trim().uppercase()
                        viewModel.onDniChanged(normalizado)
                    },
                    label = { Text("DNI/NIE") },
                    singleLine = true,
                    isError = dniValue.isNotEmpty() && !isDniValid
                )
                if (dniValue.isNotEmpty() && !isDniValid) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "DNI/NIE inválido",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(top = 4.dp),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                OutlinedTextField(
                    value = passwd.value,
                    label = { Text("Contraseña") },
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = isPasswordError,
                    trailingIcon = {
                        Image(
                            imageVector = if (!passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (!passwordVisible) "Mostrar contraseña" else "Ocultar contraseña",
                            modifier = Modifier.size(36.dp).clickable {
                                passwordVisible = !passwordVisible
                            }
                        )
                    },
                    singleLine = true
                )
                if (isPasswordError) {
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "La contraseña no puede estar vacía",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(top = 4.dp),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                Button(
                    onClick = { checkUserAndPassword(dniState.value, passwd.value, viewModel) },
                    enabled = !state.value.isLoading && dniValue.isNotEmpty() && isDniValid
                ) {
                    Text(text = "Comenzar")
                }
                Spacer(modifier = Modifier.size(4.dp))

                AnimatedVisibility(state.value.isLoading) {
                    CircularProgressIndicator()
                }

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

}

fun isValidDni(dniValue: String): Boolean {
    val dniToValidate = dniValue.trim().uppercase()
    if (dniToValidate.length != 9) return false
    val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
    // Control primer dígito del NIE
    val numeros = when (dniToValidate[0]) {
        'X' -> "0" + dniToValidate.substring(1, 8)
        'Y' -> "1" + dniToValidate.substring(1, 8)
        'Z' -> "2" + dniToValidate.substring(1, 8)
        else -> dniToValidate.substring(0, 8)
    }
    if (!numeros.all { it.isDigit() }) return false
    val numero = numeros.toIntOrNull() ?: return false
    // Control letra del DNI/NIE
    val expected = letras[numero % 23]
    return dniToValidate[8] == expected
}


private fun checkUserAndPassword(dni: String, passwd: String, viewModel: LoginViewModel) {
    // ------------------------------------------------------------------------
    // Esta función comprueba los datos, pero está obsoleta, solo para desarrollo.

    if (dni.isEmpty()) {
        println("El DNI/NIE no puede estar vacío")
        return
    }
    if (!isValidDni(dni)) {
        println("DNI/NIE inválido")
        return
    }
    if (passwd.isEmpty()) {
        println("La contraseña no puede estar vacía")
        return
    }
    // Verificar credenciales en la API
    viewModel.comenzar()
}

