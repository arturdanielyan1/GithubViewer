package com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain

import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.usecases.GetReposUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetReposUseCase(get())
    }
}