package com.luigiercrest.presentation

import org.koin.dsl.module
import com.luigiercrest.presentation.security.SecureStorage

val platformJvmModule = module {
    single<SecureStorage> { DesktopSecureStorage() }
}