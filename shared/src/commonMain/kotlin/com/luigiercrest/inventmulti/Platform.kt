package com.luigiercrest.inventmulti

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform