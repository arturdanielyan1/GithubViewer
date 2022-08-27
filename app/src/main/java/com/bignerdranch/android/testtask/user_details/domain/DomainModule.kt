package com.bignerdranch.android.testtask.user_details.domain

import com.bignerdranch.android.testtask.user_details.domain.usecases.GetUserReposUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetUserReposUseCase(get())
    }
}