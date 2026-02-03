package com.luigiercrest.presentation.home

import androidx.lifecycle.ViewModel
import com.luigiercrest.presentation.login.AuthNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _navigationState = MutableStateFlow<AuthNavigation?>(null)
    val navigationState = _navigationState.asSharedFlow()
    private val _categorias= MutableStateFlow(
        (1..10).map { "Categoria $it" }
    )
    val categorias = _categorias.asStateFlow()


}