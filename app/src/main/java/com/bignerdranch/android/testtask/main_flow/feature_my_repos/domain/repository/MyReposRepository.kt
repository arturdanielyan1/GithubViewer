package com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.repository

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import kotlinx.coroutines.flow.Flow

interface MyReposRepository {

    suspend fun getRepos(pageNumber: Int): Flow<Result<List<UserRepo>>>
}