package com.dino.library.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dinoUtilModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            "",
            Context.MODE_PRIVATE
        )
    }
}