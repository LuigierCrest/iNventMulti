package com.luigiercrest.data.database.datasource

import com.luigiercrest.data.database.dto.UsuarioDTO
import com.luigiercrest.data.datasource.NetworkResult
import com.luigiercrest.data.dto.AsignacionDTO
import com.luigiercrest.data.dto.CentroDTO
import com.luigiercrest.data.dto.DeleteResponseDTO
import com.luigiercrest.data.dto.DispositivoDTO
import com.luigiercrest.data.dto.IncidenciaDTO
import com.luigiercrest.data.dto.LoginDataDTO
import com.luigiercrest.data.dto.LoginResponseDTO
import com.luigiercrest.data.dto.ProveedorDTO
import com.luigiercrest.data.dto.ServicioTecnicoDTO
import com.luigiercrest.data.dto.ChangePasswordRequestDTO
import com.luigiercrest.data.dto.ChangePasswordResponseDTO
import com.luigiercrest.data.dto.UpdateResponseDTO
import com.luigiercrest.domain.models.UpdateResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
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
    //local P14
    // private val BASE_URL = "http://127.0.0.1:8080"

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
    private val CHANGE_PASSWORD_URL = "${USUARIOS_URL}/actualizarpasswd"


    // /api/dire
    private val DIRE_URL = "${BASE_URL}/api/dire"
    val USUARIOS_CENTRO_URL = "${DIRE_URL}/usuarioscentro"

    // /api/resp
    private val RESP_URL = "${BASE_URL}/api/resp"
    val DISPOSITIVOS_CENTRO_URL = "${RESP_URL}/dispositivoscentro"
    val INCIDENCIAS_CENTRO_URL = "${RESP_URL}/incidenciascentro"
    val SERVICIOS_CENTRO_URL = "${RESP_URL}/serviciostecnicos"

    // Autenticado: cambiar contraseña propia



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

    suspend fun getCentroHome(idCentro: String, token: String): Result<NetworkResult<CentroDTO>> {
        return try {
            val response: HttpResponse = httpClient.get(CENTROS_URL + "/${idCentro}") {
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

    suspend fun getCentros(token: String): Result<NetworkResult<List<CentroDTO>>> {
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

    suspend fun getProveedores(token: String): Result<NetworkResult<List<ProveedorDTO>>> {
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
            val result: Result<NetworkResult<List<ProveedorDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getServicios(token: String): Result<NetworkResult<List<ServicioTecnicoDTO>>> {
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
            val result: Result<NetworkResult<List<ServicioTecnicoDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getAsignaciones(token: String): Result<NetworkResult<List<AsignacionDTO>>> {
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
            val result: Result<NetworkResult<List<AsignacionDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getUsuarios(token: String): Result<NetworkResult<List<UsuarioDTO>>> {
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

    suspend fun getDispositivos(token: String): Result<NetworkResult<List<DispositivoDTO>>> {
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
            val result: Result<NetworkResult<List<DispositivoDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getIncidencias(token: String): Result<NetworkResult<List<IncidenciaDTO>>> {
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
            val result: Result<NetworkResult<List<IncidenciaDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getUsuariosCentro(
        token: String,
        idCentro: Int
    ): Result<NetworkResult<List<UsuarioDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(USUARIOS_CENTRO_URL + "/${idCentro}") {
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

    suspend fun getDispositivosCentro(
        token: String,
        idCentro: Int
    ): Result<NetworkResult<List<DispositivoDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(DISPOSITIVOS_CENTRO_URL + "/${idCentro}") {
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
            val result: Result<NetworkResult<List<DispositivoDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun getIncidenciasCentro(
        token: String,
        idCentro: Int
    ): Result<NetworkResult<List<IncidenciaDTO>>> {
        return try {
            val response: HttpResponse = httpClient.get(INCIDENCIAS_CENTRO_URL + "/${idCentro}") {
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
            val result: Result<NetworkResult<List<IncidenciaDTO>>> =
                if (response.status.isSuccess()) {
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

    suspend fun deleteDispositivo(
        token: String,
        idDispositivo: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.delete(DISPOSITIVOS_URL + "/${idDispositivo}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteDispositivo response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteIncidencia(
        token: String,
        idIncidencia: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.delete(INCIDENCIAS_URL + "/${idIncidencia}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteIncidencia response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }


    }

    suspend fun deleteUsuario(
        token: String,
        dniUsuario: String
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.delete(USUARIOS_URL + "/${dniUsuario}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteUsuario response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun deleteCentro(
        token: String,
        idCentro: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.delete(CENTROS_URL + "/${idCentro}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteCentro response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteProveedor(
        token: String,
        idProveedor: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.delete(PROVEEDORES_URL + "/${idProveedor}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteProveedor response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteServicioTecnico(
        token: String,
        idServicioTecnico: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse =
                httpClient.delete(SERVICIOS_URL + "/${idServicioTecnico}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteServicioTecnico response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteAsignacion(
        token: String,
        idAsignacion: Int
    ): Result<NetworkResult<DeleteResponseDTO>> {
        return try {
            val response: HttpResponse =
                httpClient.delete(ASIGNACIONES_URL + "/${idAsignacion}") {
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
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<DeleteResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    DeleteResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                DeleteResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - DeleteAsignacion response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun updateCentro(
        token: String,
        centroDTO: CentroDTO
    ): Result<NetworkResult<UpdateResponseDTO>> {
        return try {
            // PUT actualiza centro por idCentro
            val response: HttpResponse = httpClient.put(CENTROS_URL + "/${centroDTO.idCentro}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(centroDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }

            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateCentro response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProveedor (
        token: String,
        proveedorDTO: ProveedorDTO
    ): Result<NetworkResult<UpdateResponseDTO>> {
        return try {
            // PUT actualiza proveedor por idProveedor
            val response: HttpResponse = httpClient.put(PROVEEDORES_URL + "/${proveedorDTO.idProveedor}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(proveedorDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateProveedor response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
    suspend fun updateUsuario (
        token: String,
        usuarioDTO: UsuarioDTO
    ): Result<NetworkResult<UpdateResponseDTO>> {
        return try {
            // PUT actualiza usuario por dni
            val response: HttpResponse = httpClient.put(USUARIOS_URL + "/${usuarioDTO.dni}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(usuarioDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateUsuario response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateServicioTecnico (
        token: String,
        servicioTecnicoDTO: ServicioTecnicoDTO
    ): Result<NetworkResult<UpdateResponseDTO>>{
        return try {
            // PUT actualiza servicio técnico por idServicioTecnico
            val response: HttpResponse = httpClient.put(SERVICIOS_URL + "/${servicioTecnicoDTO.idServicioTecnico}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(servicioTecnicoDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateServicioTecnico response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun updateAsignacion (token: String, asignacionDTO: AsignacionDTO): Result<NetworkResult<UpdateResponseDTO>>{
        return try {
            // PUT actualiza asignación por idAsignacion
            val response: HttpResponse = httpClient.put(ASIGNACIONES_URL + "/${asignacionDTO.idAsignacionCompra}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(asignacionDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateAsignacion response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun updateDispositivo (token: String, dispositivoDTO: DispositivoDTO): Result<NetworkResult<UpdateResponseDTO>>{
        return try {
            // PUT actualiza dispositivo por idDispositivo
            val response: HttpResponse = httpClient.put(DISPOSITIVOS_URL + "/${dispositivoDTO.idDispositivo}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(dispositivoDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateDispositivo response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun updateIncidencia (token: String, incidenciaDTO: IncidenciaDTO): Result<NetworkResult<UpdateResponseDTO>>{
        return try {
            // PUT actualiza incidencia por idIncidencia
            val response: HttpResponse = httpClient.put(INCIDENCIAS_URL + "/actualizarincidencia/${incidenciaDTO.idIncidencia}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(incidenciaDTO)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    // Intenta parsear como JSON
                    Json.decodeFromString<UpdateResponseDTO>(body)
                } else {
                    // Si no es JSON, crea el DTO manualmente con el texto plano
                    UpdateResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                // Si falla el parseo, crea el DTO manualmente con la respueta de texto plano
                UpdateResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - UpdateIncidencia response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun changePassword(
        token: String,
        newPassword: String,
        idUsusario: Int
    ): Result<NetworkResult<ChangePasswordResponseDTO>> {
        return try {
            val response: HttpResponse = httpClient.put(CHANGE_PASSWORD_URL+"/${idUsusario}") {
                contentType(ContentType.Text.Plain)
                accept(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $token")
                }
                setBody(newPassword)
            }
            val status = response.status.value
            val body = try {
                response.bodyAsText()
            } catch (e: Exception) {
                println(e.message)
                null
            }
            val dto = try {
                if (!body.isNullOrEmpty() && body.trim().startsWith("{")) {
                    Json.decodeFromString<ChangePasswordResponseDTO>(body)
                } else {
                    ChangePasswordResponseDTO(body = body, statusCode = status)
                }
            } catch (e: Exception) {
                ChangePasswordResponseDTO(body = body, statusCode = status)
            }
            val result = Result.success(NetworkResult(dto, status, body))
            println("LOG - changePassword response Status: ${response.status}")
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}