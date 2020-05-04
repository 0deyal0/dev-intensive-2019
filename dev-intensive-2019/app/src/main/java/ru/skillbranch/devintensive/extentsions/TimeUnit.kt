package ru.skillbranch.devintensive.extentsions

import java.util.concurrent.TimeUnit

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.plural(value: Int): String {
    when (this) {
        TimeUnits.SECOND ->
            return when {
                value % 10 == 1 -> "$value секунду"
                value % 10 in 2..4 -> "$value секунды"
                else -> "$value секунд"
            }
        TimeUnits.MINUTE ->
            return when {
                value % 10 == 1 -> "$value минуту"
                value % 10 in 2..4 -> "$value минуты"
                else -> "$value минут"
            }

        TimeUnits.HOUR->
            return when {
                value % 10 == 1 -> "$value час"
                value % 10 in 2..4 -> "$value часа"
                else -> "$value часов"
            }

        TimeUnits.DAY ->
            return when {
                value % 10 == 1 -> "$value день"
                value % 10 in 2..4 -> "$value дня"
                else -> "$value дней"
            }
    }

}