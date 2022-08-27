package com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.usecases

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.repository.MyReposRepository
import kotlinx.coroutines.flow.Flow

class GetReposUseCase(
    private val myReposRepository: MyReposRepository
) {

    suspend operator fun invoke(pageNumber: Int): Flow<Result<List<UserRepo>>> {
        return myReposRepository.getRepos(pageNumber)
    }
}