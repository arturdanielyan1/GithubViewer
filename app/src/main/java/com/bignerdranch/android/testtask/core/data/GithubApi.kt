package com.bignerdranch.android.testtask.core.data

import com.bignerdranch.android.testtask.core.LOAD_USERS_BY
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("user")
    suspend fun authenticate(): UserData

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String, @Query("page") page: Int, @Query("per_page") perPage: Int): List<UserRepo>

    @GET("users/{username}/repos")
    fun getUserReposCall(@Path("username") username: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Call<List<UserRepo>>

    @GET("users")
    suspend fun getUsers(@Query("since") sinceId: Int, @Query("per_page") perPage: Int = LOAD_USERS_BY): List<UserData>
}