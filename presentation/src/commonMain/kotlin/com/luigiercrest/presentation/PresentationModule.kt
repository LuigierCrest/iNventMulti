package com.luigiercrest.presentation

import org.koin.core.module.dsl.viewModel
import com.luigiercrest.domain.Utils.LoginUtil
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get<LoginUtil>()) }
}
