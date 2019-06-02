package com.dino.library.ext

import com.google.gson.Gson

val gson = Gson()

inline fun <reified T> String.fromJson() =
    gson.fromJson(this, T::class.java)

fun Any.toJson() =
    gson.toJson(this)
