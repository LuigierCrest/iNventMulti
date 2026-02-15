package com.luigiercrest.inventmulti.di

import com.luigiercrest.presentation.DesktopSecureStorage
import com.luigiercrest.presentation.security.SecureStorage
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single<SecureStorage> { DesktopSecureStorage() }
}