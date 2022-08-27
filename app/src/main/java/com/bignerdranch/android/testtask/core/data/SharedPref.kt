package com.bignerdranch.android.testtask.core.data

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

const val PREF_LOGGED_IN = "haveLoggedIn"
const val PREF_PROFILE_PHOTO_URI = "profilePhotoUri"
const val PREF_APP_MODE = "prefSaveAppThemeMode"
const val PREF_USERNAME = "prefSaveLoggedInUserUsername"
const val PREF_TOKEN = "prefSaveToken"


fun saveAppMode(activity: Activity, mode: Int) {
    val prefEditor = activity.getPreferences(Context.MODE_PRIVATE).edit()
    prefEditor.putInt(PREF_APP_MODE, mode)
    prefEditor.apply()
}

fun getAppMode(activity: Activity): Int {
    return activity.getPreferences(Context.MODE_PRIVATE).getInt(PREF_APP_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}
