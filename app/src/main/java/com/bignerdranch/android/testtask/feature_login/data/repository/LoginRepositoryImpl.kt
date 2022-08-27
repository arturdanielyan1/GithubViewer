package com.bignerdranch.android.testtask.feature_login.data.repository

import android.content.SharedPreferences
import com.bignerdranch.android.testtask.core.TOKEN
import com.bignerdranch.android.testtask.core.USERNAME
import com.bignerdranch.android.testtask.core.data.GithubApi
import com.bignerdranch.android.testtask.core.data.PREF_LOGGED_IN
import com.bignerdranch.android.testtask.core.data.PREF_TOKEN
import com.bignerdranch.android.testtask.core.data.PREF_USERNAME
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.feature_login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginRepositoryImpl(
    private val githubApi: GithubApi,
    private val sharedPref: SharedPreferences
) : LoginRepository {

    override suspend fun authenticate(token: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading())
            TOKEN = token
            USERNAME = githubApi.authenticate().login
            saveUserData(TOKEN, USERNAME)
            emit(Result.Success(Unit))
        } catch (http: HttpException) {
            if (http.code() !in 200..299) {
                emit(Result.Error("Invalid access token"))
            } else {
                emit(Result.Error(http.message ?: "An unexpected error occurred"))
            }
        } catch (e: IOException) {
            emit(Result.Error("Check your internet connection"))
        }
    }

    override suspend fun isLoggedIn(): Result<Boolean> {
        TOKEN = sharedPref.getString(PREF_TOKEN, "") ?: ""
        USERNAME = sharedPref.getString(PREF_USERNAME, "") ?: ""
        val isLoggedIn = TOKEN.isNotEmpty()
        return Result.Success(isLoggedIn, "sendingLoggedInState")
    }

    override suspend fun setLoggedIn() {
        sharedPref.edit().putBoolean(PREF_LOGGED_IN, true).apply()
    }

    private fun saveUserData(token: String, username: String) {
        sharedPref.edit()
            .putString(PREF_TOKEN, token)
            .putString(PREF_USERNAME, username)
            .apply()
    }
}