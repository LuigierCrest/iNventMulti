package com.luigiercrest.inventmulti.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@Composable
fun Screen (content: @Composable () -> Unit) {
    val isDarkMode = isSystemInDarkTheme()
    val colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    MaterialTheme (colorScheme){
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
            //border = BorderStroke(3.dp, Red)
        )
    }
}