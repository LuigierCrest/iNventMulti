package com.luigiercrest.domain.di

import com.luigiercrest.domain.repository.CategoryRepository
import com.luigiercrest.domain.repository.ChangePasswordRepository
import com.luigiercrest.domain.repository.DetailRepository
import com.luigiercrest.domain.repository.HomeRepository
import com.luigiercrest.domain.repository.LoginRepository
import com.luigiercrest.domain.usecase.CategoryUseCase
import com.luigiercrest.domain.usecase.ChangePasswordUseCase
import com.luigiercrest.domain.usecase.DetailUseCase
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
    factory{
        CategoryUseCase(get<CategoryRepository>())
    }
    factory {
        DetailUseCase(get<DetailRepository>())
    }

    factory {
        ChangePasswordUseCase(get<ChangePasswordRepository>())
    }
}
