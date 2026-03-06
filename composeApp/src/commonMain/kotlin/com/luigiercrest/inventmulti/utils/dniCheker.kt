package com.luigiercrest.inventmulti.utils

fun isValidDni(dniValue: String): Boolean {
    val dniToValidate = dniValue.trim().uppercase()
    if (dniToValidate.length != 9) return false
    val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
    // Control primer dígito del NIE
    val numeros = when (dniToValidate[0]) {
        'X' -> "0" + dniToValidate.substring(1, 8)
        'Y' -> "1" + dniToValidate.substring(1, 8)
        'Z' -> "2" + dniToValidate.substring(1, 8)
        else -> dniToValidate.substring(0, 8)
    }
    if (!numeros.all { it.isDigit() }) return false
    val numero = numeros.toIntOrNull() ?: return false
    // Control letra del DNI/NIE
    val expected = letras[numero % 23]
    return dniToValidate[8] == expected
}