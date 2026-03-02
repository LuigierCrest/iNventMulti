package com.luigiercrest.presentation.di

import com.luigiercrest.presentation.category.CategoryViewModel
import com.luigiercrest.presentation.changePassword.ChangePasswordViewModel
import com.luigiercrest.presentation.detail.DetailViewModel
import com.luigiercrest.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import com.luigiercrest.presentation.login.LoginViewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { ChangePasswordViewModel(get(), get()) }
}
