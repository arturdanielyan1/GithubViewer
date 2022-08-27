package com.bignerdranch.android.testtask.feature_login.data

import com.bignerdranch.android.testtask.feature_login.data.repository.LoginRepositoryImpl
import com.bignerdranch.android.testtask.feature_login.domain.repository.LoginRepository
import org.koin.dsl.module

val dataModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(get(), get())
    }
}