package com.luigiercrest.presentation.security

data class AuthData(
    val token: String,
    val rol: String?,
    val expiresIn: String?,
    val idCentro: Int?,
    val idUsuario: Int?
)

interface SecureStorage {
    suspend fun saveAuth(auth: AuthData)
    suspend fun getAuth(): AuthData?
    suspend fun clearAuth()
    // suspend fun getToken(): String?
    // getRol
    // getExpiresIn
    // getIdCentro
    // getIdUsuario
}

