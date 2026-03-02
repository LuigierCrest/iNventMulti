package com.luigiercrest.inventmulti.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object SharedItemHolder {
    private val _selectedItem = MutableStateFlow<Any?>(null)
    val selectedItem = _selectedItem.asStateFlow()

    // Contador de versión para forzar recomposición aunque el item sea el mismo tipo
    private val _version = MutableStateFlow(0)
    val version = _version.asStateFlow()

    private val _isEmpty = MutableStateFlow(true)
    val isEmpty = SharedItemHolder._isEmpty.asStateFlow()

    fun setItem(item: Any?) {
        _selectedItem.value = item
        _isEmpty.value = item == null
        _version.value += 1
    }

    fun clearItem() {
        _selectedItem.value = null
        _isEmpty.value = true
        _version.value += 1
    }
}

