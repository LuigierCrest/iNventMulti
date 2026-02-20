package com.luigiercrest.inventmulti.roles

import com.luigiercrest.inventmulti.models.CategoryModel


object RoleCategories {
    fun getCategoriesByRole(rol: String): List<CategoryModel> {
        return when (rol) {
            "ADMIN" -> listAdminCategories
            "DIRE" -> listDireCategories
            "RESP" -> listRespCategories
            else -> emptyList()
        }
    }
}

val listAdminCategories = listOf(
    CategoryModel(1,"Centros", "Gestión de centros", "centros"),
    CategoryModel(2,"Proveedores", "Gestión de proveedores", "proveedores"),
    CategoryModel(3, "Serv.Técnicos", "Gestión de servicios técnicos", "servicios"),
    CategoryModel(4, "Asignaciones", "Gestión de asignaciones", "asignaciones"),
    CategoryModel(5, "Usuarios", "Gestión de usuarios", "usuarios"),
    CategoryModel(6, "Dispositivos", "Gestión de dispositivos", "dispositivos"),
    CategoryModel(7, "Incidencias", "Gestión de incidencias", "incidencias")
)

val listDireCategories = listOf(
    // los id de categoría son diferentes, ya que el rol director y responsable solo pueden acceder a la sección de exclusiva de su centro

    CategoryModel(8, "Usuarios", "Gestión de usuarios del centro", "usuarios"),
    CategoryModel(9, "Dispositivos", "Gestión de dispositivos del centro", "dispositivos"),
    CategoryModel(10, "Incidencias", "Gestión de incidencias del centro", "incidencias")
)

val listRespCategories = listOf(
    CategoryModel(9, "Dispositivos", "Gestión de dispositivos del centro", "dispositivos"),
    CategoryModel(10, "Incidencias", "Gestión de incidencias del centro", "incidencias")
)
