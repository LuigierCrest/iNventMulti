package com.luigiercrest.domain

import com.luigiercrest.domain.repository.LoginRepository
import com.luigiercrest.domain.Utils.LoginUtil
import org.koin.dsl.module


val domainModule = module {
    factory{
        LoginUtil(get<LoginRepository>())
    }
}
