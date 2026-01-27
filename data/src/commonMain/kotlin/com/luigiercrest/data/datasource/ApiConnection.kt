package com.luigiercrest.data.database.datasource

import com.luigiercrest.data.dto.LoginDataDTO
import com.luigiercrest.data.dto.LoginResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class ApiConnection(private val httpClient: HttpClient) {
    private val BASE_URL = "http://192.168.1.182:8080"
    private val LOGIN_URL = "${BASE_URL}/api/login"
    private val TEST_URL = "${BASE_URL}/api/test"
    // /api/admin necesita autenticación con token
    // /api/dire
    // /api/resp

    suspend fun login(loginData: LoginDataDTO): Result<LoginResponseDTO> {
        return try {

            val response: HttpResponse = httpClient.post(LOGIN_URL) {
                setBody(loginData)
            }
            println("LOG - Login response Status: ${response.status}")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}