package com.bignerdranch.android.testtask.user_details.domain.repository

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {

    suspend fun loadRepos(username: String, pageNumber: Int): Flow<Result<List<UserRepo>>>
}