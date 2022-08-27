package com.bignerdranch.android.testtask.user_details.domain.usecases

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.user_details.domain.repository.UserDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetUserReposUseCase(
    private val userDetailsRepository: UserDetailsRepository
) {

    suspend operator fun invoke(username: String, pageNumber: Int): Flow<Result<List<UserRepo>>> {
        return userDetailsRepository.loadRepos(username, pageNumber)
    }
}