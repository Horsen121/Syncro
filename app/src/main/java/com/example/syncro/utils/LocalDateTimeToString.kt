package com.example.syncro.utils

import java.time.LocalDateTime

fun LocalDateTime.toNormalString (): String {
    return "${this.dayOfMonth}.${this.monthValue}.${this.year} ${this.hour}:${this.minute}"
}