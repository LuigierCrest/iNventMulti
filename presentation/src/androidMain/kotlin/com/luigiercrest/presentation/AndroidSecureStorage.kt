package com.luigiercrest.presentation

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import com.luigiercrest.presentation.security.SecureStorage
import androidx.security.crypto.MasterKey
import com.luigiercrest.presentation.security.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AndroidSecureStorage(private val context: Context) : SecureStorage {
    private val prefsName =  "secure_prefs"
    private val keyToken = "auth_token"
    private val keyExpiresIn = "auth_expiresIn"
    private val keyRol = "auth_rol"
    private val keyIdUsuario = "auth_idUsuario"
    private val keyIdCentro = "auth_idCentro"

    private val prefs by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            prefsName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }
    override suspend fun saveAuth(auth: AuthData) = withContext(Dispatchers.IO) {
        val editor = prefs.edit()
        editor.putString(keyToken, auth.token)
            .putString(keyRol, auth.rol)
            .putString(keyIdUsuario, auth.idUsuario?.toString())
            .putString(keyIdCentro, auth.idCentro?.toString())

        auth.expiresIn?.let { raw ->
            val seg = when (raw) {
                is Number -> raw.toLong()
                is String -> raw.toLongOrNull()?: return@let
                else -> return@let
            }
            val expiresAtMillis = System.currentTimeMillis() + (seg * 1000L)
            editor.putString(keyExpiresIn, expiresAtMillis.toString())
        }

        editor.apply()
    }

    override suspend fun getAuth(): AuthData? = withContext(Dispatchers.IO) {
        val token = prefs.getString(keyToken, null) ?: return@withContext null

        val expiresAtStr = prefs.getString(keyExpiresIn, null)
        val expiresInSeg: Long? = expiresAtStr?.toLongOrNull()?.let { expiresAtMillis ->
            val remainingSec = (expiresAtMillis - System.currentTimeMillis()) / 1000
            if (remainingSec > 0) remainingSec else null
        }

        val rol = prefs.getString(keyRol, "") ?: ""
        val idUsuario = prefs.getString(keyIdUsuario, null) ?.toIntOrNull()
        val idCentro = prefs.getString(keyIdCentro, null) ?.toIntOrNull()
        AuthData(
            token = token,
            expiresIn = expiresInSeg?.toString(),
            rol = rol,
            idCentro = idCentro,
            idUsuario = idUsuario,
        )
    }

    override suspend fun clearAuth() = withContext(Dispatchers.IO) {
        prefs.edit().clear().apply()
    }
}