package com.luigiercrest.inventmulti.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object RefreshEventAfterDelOrUpdate {
    private val _refreshTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val refreshTrigger = _refreshTrigger.asSharedFlow()

    fun requestRefresh() {
        _refreshTrigger.tryEmit(Unit)
    }
}