package com.luigiercrest.inventmulti.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object SharedItemHolder {
    private val _selectedItem = MutableStateFlow<Any?>(null)
    val selectedItem = _selectedItem.asStateFlow()

    fun setItem (item: Any?) {
        _selectedItem.value = item
    }
}

