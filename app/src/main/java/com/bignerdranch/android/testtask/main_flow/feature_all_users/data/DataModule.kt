package com.bignerdranch.android.testtask.main_flow.feature_all_users.data

import com.bignerdranch.android.testtask.main_flow.feature_all_users.data.repository.AllUsersRepositoryImpl
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.repository.AllUsersRepository
import org.koin.dsl.module

val dataModule = module {
    single<AllUsersRepository> {
        AllUsersRepositoryImpl(get(), get())
    }
}