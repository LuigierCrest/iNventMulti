package com.luigiercrest.data.di

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.repository.CategoryRepositoryImp
import com.luigiercrest.data.repository.ChangePasswordRepositoryImp
import com.luigiercrest.data.repository.CreateRepositoryImp
import com.luigiercrest.data.repository.DetailRepositoryImp
import com.luigiercrest.data.repository.HomeRepositoryImp
import com.luigiercrest.data.repository.LoginRepositoryImp
import com.luigiercrest.domain.repository.CategoryRepository
import com.luigiercrest.domain.repository.ChangePasswordRepository
import com.luigiercrest.domain.repository.CreateRepository
import com.luigiercrest.domain.repository.DetailRepository
import com.luigiercrest.domain.repository.HomeRepository
import com.luigiercrest.domain.repository.LoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {

    single<HttpClient> {
        HttpClient{
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single {
        ApiConnection(get<HttpClient>())
    }

    single <LoginRepository> {
        LoginRepositoryImp(get<ApiConnection>())
    }

    single <HomeRepository>{
        HomeRepositoryImp(get<ApiConnection>())
    }

    single <CategoryRepository> {
        CategoryRepositoryImp(get<ApiConnection>())
    }

    single <DetailRepository> {
        DetailRepositoryImp(get<ApiConnection>())
    }

    single<ChangePasswordRepository> {
        ChangePasswordRepositoryImp(get<ApiConnection>())
    }

    single<CreateRepository> {
        CreateRepositoryImp(get<ApiConnection>())
    }
}