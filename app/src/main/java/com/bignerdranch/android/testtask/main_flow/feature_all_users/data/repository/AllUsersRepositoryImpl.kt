package com.bignerdranch.android.testtask.main_flow.feature_all_users.data.repository


import android.graphics.BitmapFactory
import com.bignerdranch.android.testtask.core.LOAD_USERS_BY
import com.bignerdranch.android.testtask.core.NO_INTERNET_MESSAGE
import com.bignerdranch.android.testtask.core.data.GithubApi
import com.bignerdranch.android.testtask.core.data.room.MyDb
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.repository.AllUsersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.URL

class AllUsersRepositoryImpl(
    private val githubApi: GithubApi,
    allUsersDb: MyDb
) : AllUsersRepository{

    private val allUsersDao = allUsersDb.allUsersDao()

    private val fullUsersList: MutableList<UserData> = mutableListOf()


    override suspend fun loadUsers(since: Int): Flow<Result<List<UserData>>> = flow {
        if(since == 0) fullUsersList.clear()
        try {
            val usersFromGithub = githubApi.getUsers(since, LOAD_USERS_BY)
            emit(Result.Loading())
            usersFromGithub.forEach { user: UserData ->
                val bitmapUrl = URL(user.avatarUrl)
                val bitmap = BitmapFactory.decodeStream(bitmapUrl.openConnection().getInputStream())
                user.avatarBitmap = bitmap
                allUsersDao.insert(user)
            }
            fullUsersList += usersFromGithub
            emit(Result.Success(fullUsersList))
        } catch (e: HttpException) {
            emit(Result.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Result.Error(NO_INTERNET_MESSAGE))
            emit(Result.Loading())
            val list = allUsersDao.getUsers(since)
            if(list.isEmpty()) {
                if(fullUsersList.lastOrNull() is UserData.Loading) {
                    fullUsersList.removeLast()
                }
            }
            fullUsersList += list
            delay(500) // fake loading so scrolling the RecyclerView would be neat
            emit(Result.Success(fullUsersList))
        }
    }
}