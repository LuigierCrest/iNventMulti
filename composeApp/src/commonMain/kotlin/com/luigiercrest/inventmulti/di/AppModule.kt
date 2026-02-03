package com.luigiercrest.inventmulti.di

import com.luigiercrest.data.di.dataModule
import com.luigiercrest.domain.di.domainModule
import com.luigiercrest.presentation.di.presentationModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    includes(
        platformModule(),
        presentationModule,
        domainModule,
        dataModule
    )
}


expect fun platformModule(): Module