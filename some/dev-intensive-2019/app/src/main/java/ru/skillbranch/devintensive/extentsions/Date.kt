package ru.skillbranch.devintensive.extentsions

import java.text.SimpleDateFormat
import java.time.Period
import java.util.*
import java.util.Calendar
import java.util.regex.Pattern


fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}



fun Date.humanizeDiff(): String {
    val diff = this.getTime() - Date().getTime();
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    if (seconds in 0..1) {
        return "только что"
    } else if (seconds in 1..45) {
        return "минуту назад"
    } else if (seconds > 75 && minutes in 1..45) {
        return when (minutes) {
            1L -> "2 минуты назад"
            in 2..4 -> "$minutes минуты назад"
            else -> "$minutes минут назад"
        }
    } else if (minutes in 45..75) {
        return "час назад"
    } else if (minutes > 75 && hours in 1..22) {
        return when (hours) {
            1L -> "2 часа назад"
            in 2..4, 22L -> "$hours часа назад"
            21L -> "$hours час назад"
            else -> "$hours часов назад"
        }
    } else if (hours in 22..26) {
        return "день назад"
    } else if (hours > 26 && days < 360) {
        when {
            days == 1L -> return "2 дня назад"
            days % 10L == 1L -> "$days день назад"
            days % 10L in 2..4 -> return "$days дня назад"
            else -> return "$days дней назад"
        }
    } else if (days > 360) {
        return "более года назад"
    } else if (seconds in -0..-1) {
        return "только что"
    } else if (seconds in -1..-45) {
        return "через минуту"
    } else if (seconds < -75 && minutes in -1..-45) {
        return when (minutes) {
            1L -> "Через 2 минуты"
            in 2..4 -> "через ${-minutes} минуты"
            else -> "через ${-minutes} минут"
        }
    } else if (minutes in -45..-75) {
        return when (hours) {
            1L -> "через 2 часа"
            in 2..4, 22L -> "через ${-hours} часа"
            21L -> "через ${-hours} час"
            else -> "через ${-hours} часов"
        }
    } else if (hours in -22..-26) {
        return "через день"
    } else if (hours < -26 && days > -360) {
        when {
            days == 1L -> return "через 2 дня"
            days % 10L == 1L -> "черех ${-days} день"
            days % 10L in 2..4 -> return "через ${-days} дня"
            else -> return "через ${-days} дней"
        }
    } else if (days < -360) {
        return "более чем через год"
    }
    return ""
}