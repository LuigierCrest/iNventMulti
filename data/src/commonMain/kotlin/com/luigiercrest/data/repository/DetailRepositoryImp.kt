package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.datasource.NetworkResult
import com.luigiercrest.data.dto.DeleteResponseDTO
import com.luigiercrest.data.dto.UpdateResponseDTO
import com.luigiercrest.data.mappers.AsignacionMapper
import com.luigiercrest.data.mappers.CentroMapper
import com.luigiercrest.data.mappers.DeleteResponseMapper
import com.luigiercrest.data.mappers.DispositivoMapper
import com.luigiercrest.data.mappers.IncidenciaMapper
import com.luigiercrest.data.mappers.ProveedorMapper
import com.luigiercrest.data.mappers.ServicioTecnicoMapper
import com.luigiercrest.data.mappers.UsuarioMapper
import com.luigiercrest.domain.models.AsignacionModel
import com.luigiercrest.domain.models.CentroModel
import com.luigiercrest.domain.models.DeleteResponseModel
import com.luigiercrest.domain.models.DispositivoModel
import com.luigiercrest.domain.models.IncidenciaModel
import com.luigiercrest.domain.models.ProveedorModel
import com.luigiercrest.domain.models.ServicioTecnicoModel
import com.luigiercrest.domain.models.UpdateResponseModel
import com.luigiercrest.domain.models.UsuarioModel
import com.luigiercrest.domain.repository.DetailRepository

class DetailRepositoryImp (private val apiConnection: ApiConnection) : DetailRepository {
    // Actualizar, borrar y crear nuevos registros
    override suspend fun deleteDispositivo(
        token: String,
        idDispositivo: Int
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteDispositivo(token, idDispositivo)
            if (netResult.isFailure) {
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!

            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteDispositivo 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el dispositivo porque tiene incidencia abierta)")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando dispositivo: HTTP ${network.status}"))
                }
            }

        }catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun deleteIncidencia(
        token: String,
        idIncidencia: Int
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteIncidencia(token, idIncidencia)
            if (netResult.isFailure) {
                println("LOG- Error en deleteIncidencia en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            if (network.body != null) {
                val model = DeleteResponseMapper.toDomain(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.rawBody} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en deleteIncidencia IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteUsuario(
        token: String,
        dniUsuario: String
    ): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteUsuario(token, dniUsuario)
            if (netResult.isFailure) {
                println("LOG- Error en deleteUsuario en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteUsuario 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el usuario porque tiene incidencias asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando usuario: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception) {
            println("LOG- Error en deleteUsuario IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteCentro(token: String, idCentro: Int): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteCentro(token, idCentro)
            if (netResult.isFailure) {
                println("LOG- Error en deleteCentro en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteCentro 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el centro porque tiene dependencias asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando centro: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en deleteCentro IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteProveedor (token: String, idProveedor:Int): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteProveedor(token, idProveedor)
            if (netResult.isFailure) {
                println("LOG- Error en deleteProveedor en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteProveedor 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el proveedor porque tiene asignaciones asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando proveedor: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en deleteProveedor IMP: ${e.message}")
            Result.failure(e)
        }
    }
    override suspend fun deleteServicioTecnico(token: String, idServicioTecnico : Int): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteServicioTecnico(token, idServicioTecnico)
            if (netResult.isFailure) {
                println("LOG- Error en deleteServicioTecnico en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteServicioTecnico 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar el servicio técnico porque tiene incidencias asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando servicio técnico: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en deleteServicioTecnico IMP: ${e.message}")
            Result.failure(e)
        }
    }
    override suspend fun deleteAsignacion(token: String, idAsignacion : Int): Result<DeleteResponseModel> {
        return try {
            val netResult = apiConnection.deleteAsignacion(token, idAsignacion)
            if (netResult.isFailure) {
                println("LOG- Error en deleteAsignacion en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as DeleteResponseDTO
                    val model = DeleteResponseMapper.toDomain(dto, network.status)
                    Result.success(model)
                }
                500 -> {
                    println("LOG- deleteAsignacion 500: ${network.rawBody}")
                    Result.failure(
                        Exception("No se puede eliminar la asignación porque tiene dependencias asociadas")
                    )
                }
                else -> {
                    Result.failure(Exception("Error eliminando asignación: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en deleteAsignacion IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateCentro(token: String, centroModel: CentroModel): Result<UpdateResponseModel> {
            return try {
                val netResult = apiConnection.updateCentro(token, CentroMapper.toDTO(centroModel))
                if (netResult.isFailure) {
                    println("LOG- Error en updateCentro en llamada a la API: ${netResult.exceptionOrNull()}")
                    return Result.failure(netResult.exceptionOrNull()!!)
                }
                val network = netResult.getOrNull()!!
                when (network.status) {
                    200 -> {
                        val dto = network.body as UpdateResponseDTO
                        val model = UpdateResponseModel(
                            body = dto.body ?: "Centro actualizado correctamente",
                            statusCode = network.status
                        )
                        Result.success(model)
                    }
                    else -> {
                        Result.failure(Exception("Error actualizando centro: HTTP ${network.status}"))
                    }
                }
            } catch (e: Exception){
                println("LOG- Error en updateCentro IMP: ${e.message}")
                Result.failure(e)
            }
    }
    override suspend fun updateProveedor(token: String, proveedorModel: ProveedorModel): Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateProveedor(token, ProveedorMapper.toDTO(proveedorModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateProveedor en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Proveedor actualizado correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando proveedor: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateProveedor IMP: ${e.message}")
            Result.failure(e)
        }
    }
    override suspend fun updateUsuario(token: String, usuarioModel: UsuarioModel): Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateUsuario(token, UsuarioMapper.toDTO(usuarioModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateUsuario en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Usuario actualizado correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando usuario: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateUsuario IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateServicioTecnico(token: String, servicioTecnicoModel: ServicioTecnicoModel) : Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateServicioTecnico(token, ServicioTecnicoMapper.toDTO(servicioTecnicoModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateServicioTecnico en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Servicio técnico actualizado correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando servicio técnico: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateServicioTecnico IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateAsignacion(
        token: String,
        asignacionModel: AsignacionModel
    ): Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateAsignacion(token, AsignacionMapper.toDTO(asignacionModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateAsignacion en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Asignación actualizada correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando asignación: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateAsignacion IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateDispositivo(
        token: String,
        dispositivoModel: DispositivoModel
    ): Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateDispositivo(token, DispositivoMapper.toDTO(dispositivoModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateDispositivo en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Dispositivo actualizado correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando dispositivo: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateDispositivo IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun updateIncidencia(token: String, incidenciaModel: IncidenciaModel): Result<UpdateResponseModel> {
        return try {
            val netResult = apiConnection.updateIncidencia(token, IncidenciaMapper.toDTO(incidenciaModel))
            if (netResult.isFailure) {
                println("LOG- Error en updateIncidencia en llamada a la API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            when (network.status) {
                200 -> {
                    val dto = network.body as UpdateResponseDTO
                    val model = UpdateResponseModel(
                        body = dto.body ?: "Incidencia actualizada correctamente",
                        statusCode = network.status
                    )
                    Result.success(model)
                }
                else -> {
                    Result.failure(Exception("Error actualizando incidencia: HTTP ${network.status}"))
                }
            }
        } catch (e: Exception){
            println("LOG- Error en updateIncidencia IMP: ${e.message}")
            Result.failure(e)
        }
    }




}