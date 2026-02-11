package com.luigiercrest.data.database.datasource

import com.luigiercrest.data.datasource.NetworkResult
import com.luigiercrest.data.dto.LoginDataDTO
import com.luigiercrest.data.dto.LoginResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

class ApiConnection(private val httpClient: HttpClient) {
    //private val BASE_URL = "http://192.168.1.182:8080"
    private val BASE_URL = "https://inventapi.mooo.com"

    // local P14s = "http://192.168.1.182:8080"
    private val LOGIN_URL = "${BASE_URL}/api/login"
    // private val TEST_URL = "${BASE_URL}/api/test"
    // necesitan autenticación con token
    // /api/admin
    // /api/dire
    // /api/resp

    suspend fun login(loginData: LoginDataDTO): Result<NetworkResult<LoginResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.post(LOGIN_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(loginData)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                null
            }

            val result: Result<NetworkResult<LoginResponseDTO>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<LoginResponseDTO>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                // Devuelve el body del error y el código para que capas superiores lo muestren
                Result.success(NetworkResult(null, status, body))
            }

            println("LOG - Login response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}