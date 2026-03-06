/*
*
*   iNvent Multi es una aplicación de gestión de inventarios que permite a los usuarios llevar
*   un control detallado de los dispositivos de un centro docente.
*   iNvent Multi ofrece funcionalidades como la creación y edición de usuarios e incidencias,
*   gestión de dispositivos y sus incidencias. La aplicación está diseñada para ser multiplataforma,
*   lo que permite a los usuarios acceder a través de un smartphone Android o de escritorio.
*
*   Desarrollado por Luis Manuel Ortega Rodríguez, 3ºDAM, IES El Rincón, Las Palmas de Gran Canaria. Curso 2025-2026.
*
*   Licencia: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
*
* */

package com.luigiercrest.inventmulti

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.luigiercrest.inventmulti.navigation.NavRoot

@Composable
fun App() {
    MaterialTheme {
        NavRoot()
    }
}
