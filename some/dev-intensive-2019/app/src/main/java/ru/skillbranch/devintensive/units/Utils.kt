package ru.skillbranch.devintensive.units

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var trimedFullName = fullName?.trim()
        if (trimedFullName == "") trimedFullName = null
        var parts: List<String>? = trimedFullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, devider: String = " "): String {
        val dict = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
        var parts: List<String>? = payload.split(" ")

        var translited = ""
        var tranlitedWords = ArrayList<String>()
        parts?.forEach {
            var tranlitedWord = ""
            it.toCharArray().forEach {
                var translitedSymbol = it.toString().toLowerCase()

                if (translitedSymbol != null) {
                    when {
                        translitedSymbol.toLowerCase() in dict.values -> {
                            tranlitedWord =
                                tranlitedWord.plus(translitedSymbol)
                        }
                        translitedSymbol.toLowerCase() in dict.keys -> {
                            translitedSymbol = dict.getValue(it.toString().toLowerCase())
                            tranlitedWord = tranlitedWord.plus(translitedSymbol)
                        }
                        else -> tranlitedWord = tranlitedWord.plus("")
                    }
                }
            }
            tranlitedWords.add(tranlitedWord.capitalize())
        }
        translited = tranlitedWords.joinToString(separator = devider)

        return translited
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstNameInital = if (firstName?.trim() == "") null else firstName?.get((0))
        var lastNameInital = if (firstName?.trim() == "") null else lastName?.get((0))

        if (firstNameInital != null && lastNameInital != null)
            return firstNameInital.toString()?.plus(lastNameInital)
        else if (firstNameInital != null)
            return firstNameInital.toString()
        else if (lastNameInital != null)
            return lastNameInital.toString()
        else
            return null
    }
}