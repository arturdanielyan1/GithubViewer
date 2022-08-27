package com.bignerdranch.android.testtask.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { getSharedPrefs(androidApplication()) }
}

private fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        "test_task_shared_preferences",
        Context.MODE_PRIVATE
    )
}