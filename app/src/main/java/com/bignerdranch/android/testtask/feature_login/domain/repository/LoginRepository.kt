package com.bignerdranch.android.testtask.feature_login.domain.repository

import com.bignerdranch.android.testtask.core.model.Result
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun authenticate(token: String): Flow<Result<Unit>>

    suspend fun isLoggedIn(): Result<Boolean>

    suspend fun setLoggedIn()
}