package com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.usecases

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.repository.AllUsersRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val allUsersRepository: AllUsersRepository
) {

    suspend operator fun invoke(since: Int): Flow<Result<List<UserData>>> {
        return allUsersRepository.loadUsers(since)
    }

}