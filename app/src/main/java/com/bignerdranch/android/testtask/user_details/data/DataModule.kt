package com.bignerdranch.android.testtask.user_details.data

import com.bignerdranch.android.testtask.user_details.data.repository.UserDetailsRepositoryImpl
import com.bignerdranch.android.testtask.user_details.domain.repository.UserDetailsRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserDetailsRepository> {
        UserDetailsRepositoryImpl(get())
    }
}