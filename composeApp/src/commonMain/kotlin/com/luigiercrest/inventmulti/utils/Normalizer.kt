package com.luigiercrest.inventmulti.utils

fun String.normalize(): String {
    return this.lowercase()
        .replace("á", "a").replace("é", "e").replace("í", "i")
        .replace("ó", "o").replace("ú", "u").replace("ü", "u")
}