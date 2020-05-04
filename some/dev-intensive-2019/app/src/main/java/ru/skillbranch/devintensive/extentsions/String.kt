package ru.skillbranch.devintensive.extentsions

fun String.truncate(count: Int = 16): String {
    var trimed = this.trim()
    if (trimed.length > count - 3 && trimed.length > 4) {
        trimed = trimed.substring(0, count).trim() + "..."
    }
    return trimed
}
fun String.stripHtml(): String {
    var striped = this.replace("[\\s]{2,}".toRegex(), " ").replace("<[^>]*>".toRegex(), "")

    return striped
}