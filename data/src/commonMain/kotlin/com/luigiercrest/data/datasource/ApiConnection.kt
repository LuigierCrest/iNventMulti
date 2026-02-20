package com.luigiercrest.data.database.datasource

import com.luigiercrest.data.database.dto.UsuarioDTO
import com.luigiercrest.data.datasource.NetworkResult
import com.luigiercrest.data.dto.AsignacionDTO
import com.luigiercrest.data.dto.CentroDTO
import com.luigiercrest.data.dto.DispositivoDTO
import com.luigiercrest.data.dto.IncidenciaDTO
import com.luigiercrest.data.dto.LoginDataDTO
import com.luigiercrest.data.dto.LoginResponseDTO
import com.luigiercrest.data.dto.ProveedorDTO
import com.luigiercrest.data.dto.ServicioTecnicoDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

class ApiConnection(private val httpClient: HttpClient) {
    //local P14
    //private val BASE_URL = "http://192.168.1.182:8080"

    // VPS
    private val BASE_URL = "https://inventapi.mooo.com"

    val LOGIN_URL = "${BASE_URL}/api/login"

    // Authenticate

    // /api/admin
    private val ADMIN_URL = "${BASE_URL}/api/admin"
    val CENTROS_URL = "${ADMIN_URL}/centros"
    val PROVEEDORES_URL = "${ADMIN_URL}/proveedores"
    val SERVICIOS_URL = "${ADMIN_URL}/serviciostecnicos"
    val ASIGNACIONES_URL = "${ADMIN_URL}/asignacioncompras"
    val USUARIOS_URL = "${ADMIN_URL}/usuarios"
    val DISPOSITIVOS_URL = "${ADMIN_URL}/dispositivos"
    val INCIDENCIAS_URL = "${ADMIN_URL}/incidencias"


    // /api/dire
    private val DIRE_URL = "${BASE_URL}/api/dire"
    val USUARIOS_CENTRO_URL = "${DIRE_URL}/usuarioscentro"


    // /api/resp
    private val RESP_URL = "${BASE_URL}/api/resp"
    val DISPOSITIVOS_CENTRO_URL = "${RESP_URL}/dispositivoscentro"
    val INCIDENCIAS_CENTRO_URL = "${RESP_URL}/incidenciascentro"
    val SERVICIOS_CENTRO_URL = "${RESP_URL}/serviciostecnicos"






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
                println(e.message)
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

    suspend fun getCentroHome (idCentro:String, token:String): Result<NetworkResult<CentroDTO>> {
        return try {
            val response: HttpResponse = httpClient.get(CENTROS_URL+"/${idCentro}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<CentroDTO>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<CentroDTO>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }

            println("LOG - GetCentroHome ${idCentro} response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCentros (token:String): Result<NetworkResult<List<CentroDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(CENTROS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<CentroDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<CentroDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getProveedores (token:String): Result<NetworkResult<List<ProveedorDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(PROVEEDORES_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<ProveedorDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<ProveedorDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getServicios (token:String): Result<NetworkResult<List<ServicioTecnicoDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(SERVICIOS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<ServicioTecnicoDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<ServicioTecnicoDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getAsignaciones (token:String): Result<NetworkResult<List<AsignacionDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(ASIGNACIONES_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<AsignacionDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<AsignacionDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getUsuarios (token:String): Result<NetworkResult<List<UsuarioDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(USUARIOS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<UsuarioDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<UsuarioDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getDispositivos (token:String): Result<NetworkResult<List<DispositivoDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(DISPOSITIVOS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<DispositivoDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<DispositivoDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getIncidencias (token:String): Result<NetworkResult<List<IncidenciaDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(INCIDENCIAS_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<IncidenciaDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<IncidenciaDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getUsuariosCentro (token:String, idCentro:Int): Result<NetworkResult<List<UsuarioDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(USUARIOS_CENTRO_URL+"/${idCentro}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<UsuarioDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<UsuarioDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getDispositivosCentro (token:String, idCentro:Int): Result<NetworkResult<List<DispositivoDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(DISPOSITIVOS_CENTRO_URL+"/${idCentro}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<DispositivoDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<DispositivoDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getIncidenciasCentro (token:String, idCentro:Int): Result<NetworkResult<List<IncidenciaDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(INCIDENCIAS_CENTRO_URL+"/${idCentro}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val result: Result<NetworkResult<List<IncidenciaDTO>>> = if (response.status.isSuccess()) {
                val dto = if (!body.isNullOrEmpty()) {
                    Json.decodeFromString<List<IncidenciaDTO>>(body)
                } else {
                    null
                }
                Result.success(NetworkResult(dto, status, body))
            } else {
                Result.success(NetworkResult(null, status, body))
            }
            println("LOG - GetCentros response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}