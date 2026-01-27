package com.luigiercrest.inventmulti

import com.luigiercrest.data.dataModule
import com.luigiercrest.domain.domainModule
import com.luigiercrest.presentation.presentationModule
import org.koin.dsl.module

val appModule = listOf(
    platformModule(), presentationModule, domainModule, dataModule
)

expect fun platformModule(): org.koin.core.module.Module