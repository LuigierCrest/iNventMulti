package com.luigiercrest.presentation

import org.koin.dsl.module
import com.luigiercrest.presentation.security.SecureStorage

val platformAndroidModule = module {
    single<SecureStorage> { AndroidSecureStorage(get()) } // get() resolverá el Context (androidContext fue pasado en startKoin)
}