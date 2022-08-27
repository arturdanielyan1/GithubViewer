package com.bignerdranch.android.testtask.core

import android.util.Log

var TOKEN = ""

var USERNAME = ""


const val LOAD_USERS_BY = 8
const val LOAD_REPOS_BY = 2

const val NO_INTERNET_MESSAGE = "Check your internet connection"

inline fun log(obj: Any?) = Log.d("myLogs", obj.toString())