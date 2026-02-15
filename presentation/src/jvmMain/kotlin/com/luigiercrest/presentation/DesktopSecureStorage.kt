package com.luigiercrest.presentation

import com.luigiercrest.presentation.security.AuthData
import com.luigiercrest.presentation.security.SecureStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.prefs.Preferences
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.SecretKeyFactory
import java.util.Base64
import kotlin.random.Random

class DesktopSecureStorage : SecureStorage {
    private val prefs = Preferences.userNodeForPackage(DesktopSecureStorage::class.java)
    private val keyToken = "auth_token_enc"
    private val keyExpiresIn = "auth_expires_in_enc"
    private val keyRol = "auth_rol_enc"
    private val keyIdUsuario = "auth_id_usuario_enc"
    private val keyIdCentro = "auth_id_centro_enc"

    private fun getAppSecret(): String {
        // 1. Intentar desde variable de entorno
        System.getenv("APP_SECRET")?.let { return it }
        // 2. Intentar desde archivo local.properties en raíz del proyecto
        val projectRoot = File(System.getProperty("user.dir"))
        val localPropsFile = File(projectRoot, "local.properties")
        if (localPropsFile.exists()) {
            localPropsFile.readLines().forEach { line ->
                if (line.startsWith("APP_SECRET=")) {
                    return line.substringAfter("APP_SECRET=").trim()
                }
            }
        }
        // 3. Generar secreto por defecto (solo para desarrollo)
        val defaultSecret = "Una-Clave-muy-secreta-solo-para-desarrollo-no-usar-en-producción-1234"
        println("⚠️  ADVERTENCIA: Usando secreto por defecto. Para producción, configura APP_SECRET en:")
        println("   - Variable de entorno: APP_SECRET")
        println("   - O archivo: ${localPropsFile.absolutePath}")
        return defaultSecret
    }

    private fun deriveKey(): SecretKey {
        val secret = getAppSecret()
        val spec = PBEKeySpec(secret.toCharArray(), "fixed_salt".toByteArray(), 10000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val bytes = factory.generateSecret(spec).encoded
        return SecretKeySpec(bytes, "AES")
    }

    private fun encrypt(plain: String?, key: SecretKey): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val iv = ByteArray(12).also { Random.nextBytes(it) }
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, spec)

        val encrypted = cipher.doFinal(plain?.toByteArray(Charsets.UTF_8))
        val combined = iv + encrypted
        return Base64.getEncoder().encodeToString(combined)
    }

    private fun decrypt(enc: String, key: SecretKey): String {
        val combined = Base64.getDecoder().decode(enc)
        val iv = combined.copyOfRange(0, 12)
        val cipherText = combined.copyOfRange(12, combined.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)
        return String(cipher.doFinal(cipherText), Charsets.UTF_8)
    }

    override suspend fun saveAuth(auth: AuthData) = withContext(Dispatchers.IO) {
        val key = deriveKey()
        prefs.put(keyToken, encrypt(auth.token, key))
        prefs.put(keyRol, encrypt(auth.rol, key))
        prefs.put(keyIdUsuario, encrypt(auth.idUsuario?.toString(), key))
        prefs.put(keyIdCentro, encrypt(auth.idCentro?.toString(), key))

        auth.expiresIn?.let {raw ->
            val secsString = raw.toString()
            val secs = secsString.toLongOrNull() ?: return@let
            val expiresAtMillis = System.currentTimeMillis() + secs * 1000L
            prefs.put(keyExpiresIn, encrypt(expiresAtMillis.toString(), key))
        }

        prefs.flush()
    }

    override suspend fun getAuth(): AuthData? = withContext(Dispatchers.IO) {
        val key = deriveKey()
        val t = prefs.get(keyToken, null) ?: return@withContext null

        val encRol = prefs.get(keyRol, "") ?: ""
        val encUser = prefs.get(keyIdUsuario, "") ?: ""
        val encCenter = prefs.get(keyIdCentro, "") ?: ""
        val encExpiresIn = prefs.get(keyExpiresIn, "") ?: ""


        val rol = if (encRol.isNotEmpty()) decrypt(encRol, key) else ""
        val idUsuario: Int? = if (encUser.isNotEmpty()) decrypt(encUser, key).toIntOrNull() else null
        val idCentro: Int? = if (encCenter.isNotEmpty()) decrypt(encCenter, key).toIntOrNull() else null

        val expiresInRemainingSec: Long? = if (encExpiresIn.isNotEmpty()) {
            decrypt(encExpiresIn, key).toLongOrNull()?.let{ expiresAtMillis ->
                val remaining = (expiresAtMillis - System.currentTimeMillis()) / 1000
                if (remaining > 0) remaining else null
            }
        } else null

        AuthData(
            token = decrypt(t, key),
            expiresIn = expiresInRemainingSec?.toString(),
            rol = rol,
            idUsuario = idUsuario,
            idCentro = idCentro,
        )
    }

    override suspend fun clearAuth() = withContext(Dispatchers.IO) {
        prefs.remove(keyToken)
        prefs.remove(keyExpiresIn)
        prefs.remove(keyRol)
        prefs.remove(keyIdUsuario)
        prefs.remove(keyIdCentro)
        prefs.flush()
    }
}