package com.luigiercrest.data.repository

import com.luigiercrest.data.database.datasource.ApiConnection
import com.luigiercrest.data.mappers.AsignacionesResponseMapper
import com.luigiercrest.data.mappers.CentrosResponseMapper
import com.luigiercrest.data.mappers.DispositivosResponseMapper
import com.luigiercrest.data.mappers.IncidenciasResponseMapper
import com.luigiercrest.data.mappers.ProveedoresResponseMapper
import com.luigiercrest.data.mappers.ServiciosResponseMapper
import com.luigiercrest.data.mappers.UsuariosResponseMapper
import com.luigiercrest.domain.models.AsignacionResponseModel
import com.luigiercrest.domain.models.CentroResponseModel
import com.luigiercrest.domain.models.DispositivoResponseModel
import com.luigiercrest.domain.models.IncidenciaResponseModel
import com.luigiercrest.domain.models.ProveedorResponseModel
import com.luigiercrest.domain.models.ServicioTecnicoResponseModel
import com.luigiercrest.domain.models.UsuarioResponseModel
import com.luigiercrest.domain.repository.CategoryRepository

class CategoryRepositoryImp(private val connection: ApiConnection) : CategoryRepository {
    override suspend fun getCentros(token: String): Result<List<CentroResponseModel>> {
        return try {
            val netResult = connection.getCentros(token)
            if (netResult.isFailure) {
                println("LOG- Error en getCentros en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = CentrosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getCentros IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getProveedores(token: String): Result<List<ProveedorResponseModel>> {
        return try {
            val netResult = connection.getProveedores(token)
            if (netResult.isFailure) {
                println("LOG- Error en getProveedores en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = ProveedoresResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getProveedores IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getServicios(token: String): Result<List<ServicioTecnicoResponseModel>> {
        return try {
            val netResult = connection.getServicios(token)
            if (netResult.isFailure) {
                println("LOG- Error en getServicios en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = ServiciosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }

        } catch (e: Exception) {
            println("LOG- Error en getServicios IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getAsignaciones(token: String): Result<List<AsignacionResponseModel>> {
        return try {
            val netResult = connection.getAsignaciones(token)
            if (netResult.isFailure) {
                println("LOG- Error en getAsignaciones en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = AsignacionesResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getAsignaciones IMP: ${e.message}")
            Result.failure(e)
        }

    }

    override suspend fun getUsuarios(token: String): Result<List<UsuarioResponseModel>> {
        return try {
            val netResult = connection.getUsuarios(token)
            if (netResult.isFailure) {
                println("LOG- Error en getUsuarios en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = UsuariosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getUsuarios IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getDispositivos(token: String): Result<List<DispositivoResponseModel>> {
        return try {
            val netResult = connection.getDispositivos(token)
            if (netResult.isFailure) {
                println("LOG- Error en getDispositivos en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = DispositivosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getDispositivos IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getIncidencias(token: String): Result<List<IncidenciaResponseModel>> {
        return try {
            val netResult = connection.getIncidencias(token)
            if (netResult.isFailure) {
                println("LOG- Error en getIncidencias en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = IncidenciasResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getIncidencias IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getUsuariosCentro(
        token: String,
        idCentro: Int
    ): Result<List<UsuarioResponseModel>> {
        return try {
            val netResult = connection.getUsuariosCentro(token, idCentro)
            if (netResult.isFailure) {
                println("LOG- Error en getUsuariosCentro en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = UsuariosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getUsuariosCentro IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getDispositivosCentro(
        token: String,
        idCentro: Int
    ): Result<List<DispositivoResponseModel>> {
        return try {
            val netResult = connection.getDispositivosCentro(token, idCentro)
            if (netResult.isFailure) {
                println("LOG- Error en getDispositivosCentro en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = DispositivosResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getDispositivosCentro IMP: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getIncidenciasCentro(
        token: String,
        idCentro: Int
    ): Result<List<IncidenciaResponseModel>> {
        return try {
            val netResult = connection.getIncidenciasCentro(token, idCentro)
            if (netResult.isFailure) {
                println("LOG- Error en getIncidenciasCentro en llamada a al API: ${netResult.exceptionOrNull()}")
                return Result.failure(netResult.exceptionOrNull()!!)
            }
            val network = netResult.getOrNull()!!
            return if (network.body != null) {
                val model = IncidenciasResponseMapper.map(network.body, network.status)
                Result.success(model)
            } else {
                val msg = "HTTP ${network.status}: ${network.body} Sin cuerpo"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            println("LOG- Error en getIncidenciasCentro IMP: ${e.message}")
            Result.failure(e)
        }
    }

}