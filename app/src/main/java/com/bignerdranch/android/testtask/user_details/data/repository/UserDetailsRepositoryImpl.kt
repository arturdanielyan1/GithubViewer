package com.bignerdranch.android.testtask.user_details.data.repository

import com.bignerdranch.android.testtask.core.LOAD_REPOS_BY
import com.bignerdranch.android.testtask.core.NO_INTERNET_MESSAGE
import com.bignerdranch.android.testtask.core.data.GithubApi
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.user_details.domain.repository.UserDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserDetailsRepositoryImpl(
    private val githubApi: GithubApi
) : UserDetailsRepository {


    override suspend fun loadRepos(username: String, pageNumber: Int): Flow<Result<List<UserRepo>>> = flow {
        try {
            emit(Result.Loading())
            val repoList = githubApi.getUserRepos(username, pageNumber, LOAD_REPOS_BY)
            if(repoList.isEmpty()){
                emit(Result.Success(emptyList()))
                return@flow
            }
            emit(Result.Success(repoList))
        } catch (e: HttpException) {
            emit(Result.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Result.Error(NO_INTERNET_MESSAGE))
        }
    }
}