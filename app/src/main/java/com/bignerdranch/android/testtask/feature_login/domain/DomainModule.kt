package com.bignerdranch.android.testtask.feature_login.domain

import com.bignerdranch.android.testtask.feature_login.domain.usecases.IsLoggedInUseCase
import com.bignerdranch.android.testtask.feature_login.domain.usecases.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        LoginUseCase(get())
    }

    single {
        IsLoggedInUseCase(get())
    }
}