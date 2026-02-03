package com.luigiercrest.domain.models

data class AdminCategory (
    val categoria: String
)

val listAdminCategories = listOf(
    AdminCategory("Centros"),
    AdminCategory("Proveedores"),
    AdminCategory("Serv.Técnicos"),
    AdminCategory("Asignaciones"),
    AdminCategory("Usuarios"),
    AdminCategory("Dispositivos"),
    AdminCategory("Incidencias")
)