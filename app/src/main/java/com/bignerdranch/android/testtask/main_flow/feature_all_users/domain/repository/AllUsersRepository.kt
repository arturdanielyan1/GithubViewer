package com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.repository

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {

    suspend fun loadUsers(since: Int): Flow<Result<List<UserData>>>
}