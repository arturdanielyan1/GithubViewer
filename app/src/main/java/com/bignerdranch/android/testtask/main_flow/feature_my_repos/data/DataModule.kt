package com.bignerdranch.android.testtask.main_flow.feature_my_repos.data

import com.bignerdranch.android.testtask.main_flow.feature_my_repos.data.repository.MyReposRepositoryImpl
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.repository.MyReposRepository
import org.koin.dsl.module

val dataModule = module {
    single<MyReposRepository> {
        MyReposRepositoryImpl(get(), get())
    }
}