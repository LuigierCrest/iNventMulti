package com.luigiercrest.inventmulti.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

object DateUtil {
    fun getCurrentDate(): String {
        val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        return date.toString() // Formato "YYYY-MM-DD"
    }
}