package com.bignerdranch.android.testtask.main_flow.feature_all_users.domain

import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.usecases.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetUsersUseCase(get())
    }
}