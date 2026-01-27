package com.luigiercrest.data

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.repository.LoginRepositoryImp
import com.luigiercrest.domain.repository.LoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
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
                logger = io.ktor.client.plugins.logging.Logger.DEFAULT
                level = io.ktor.client.plugins.logging.LogLevel.ALL
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
}