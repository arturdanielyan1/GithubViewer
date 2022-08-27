package com.bignerdranch.android.testtask.main_flow.feature_my_repos.data.repository

import com.bignerdranch.android.testtask.core.LOAD_REPOS_BY
import com.bignerdranch.android.testtask.core.NO_INTERNET_MESSAGE
import com.bignerdranch.android.testtask.core.USERNAME
import com.bignerdranch.android.testtask.core.data.GithubApi
import com.bignerdranch.android.testtask.core.data.room.MyDb
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.repository.MyReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class MyReposRepositoryImpl(
    private val githubApi: GithubApi,
    myReposDb: MyDb
) : MyReposRepository {

    private val myReposDao = myReposDb.myReposDao()


    override suspend fun getRepos(pageNumber: Int): Flow<Result<List<UserRepo>>> = flow {
        try {
            emit(Result.Loading())
            val repoList = githubApi.getUserRepos(USERNAME, pageNumber, LOAD_REPOS_BY)
            if(repoList.isEmpty()){
                emit(Result.Success(emptyList()))
                return@flow
            }
            repoList.forEach {
                if (it.language == null) { // not always false
                    myReposDao.insert(
                        UserRepo(it.rowId, it.name, "unknown")
                    )
                } else{
                    myReposDao.insert(it)
                }
            }
            emit(Result.Success(repoList))
        } catch (e: HttpException) {
            emit(Result.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Result.Error(NO_INTERNET_MESSAGE))

            val dbSize = myReposDao.getCount()

            val lastRepos = myReposDao.getRepos(0)
            emit(Result.Success(lastRepos))
            for (i in LOAD_REPOS_BY until dbSize step LOAD_REPOS_BY) {
                val reposFromDb = myReposDao.getRepos(lastRepos[lastRepos.lastIndex].rowId)
                emit(Result.Success(reposFromDb))
                emit(Result.Loading())
            }
            emit(Result.Success(emptyList())) // this notifies that everything is loaded
        }
    }
}