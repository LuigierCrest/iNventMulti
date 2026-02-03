package com.luigiercrest.data.datasource

data class NetworkResult<T> (
    val body: T?,
    val status: Int,
    val rawBody: String?
)