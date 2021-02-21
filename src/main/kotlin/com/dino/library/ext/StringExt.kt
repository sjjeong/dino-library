package com.dino.library.ext

fun String.extractNumber(): String {
    return replace(Regex("[^\\d]"), "")
}

fun String.extractCapEnglish(): String {
    return replace(Regex("[^A-Z\\s]"), "")
}

fun String.extractCapEnglishNumber(): String {
    return replace(Regex("[^A-Z0-9\\s]"), "")
}

fun String.extractEngAddress(): String {
    return replace(Regex("[^A-Za-z0-9 \\-,.\\s/]"), "")
}

fun String.masking(maskText: String = "◼"): String {
    return replace(Regex("[^-()+.@\\s]"), maskText)
}
