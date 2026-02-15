package com.luigiercrest.domain.di

import com.luigiercrest.domain.repository.HomeRepository
import com.luigiercrest.domain.repository.LoginRepository
import com.luigiercrest.domain.usecase.HomeUseCase
import com.luigiercrest.domain.usecase.LoginUseCase
import org.koin.dsl.module


val domainModule = module {
    factory{
        LoginUseCase(get<LoginRepository>())
    }
    factory{
        HomeUseCase(get<HomeRepository>())
    }
}
