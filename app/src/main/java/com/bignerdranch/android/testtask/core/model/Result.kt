package com.bignerdranch.android.testtask.core.model

sealed class Result<out T>(val data: T? = null, val message: String? = null) {

    class Success<out T>(data: T, message: String? = null) : Result<T>(data)
    class Loading<out T>(data: T? = null) : Result<T>(data)
    class Error<out T>(message: String) : Result<T>(null, message)
}