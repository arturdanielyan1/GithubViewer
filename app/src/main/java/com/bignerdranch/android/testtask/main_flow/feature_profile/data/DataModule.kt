package com.bignerdranch.android.testtask.main_flow.feature_profile.data

import com.bignerdranch.android.testtask.main_flow.feature_profile.data.repository.ProfileRepositoryImpl
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository.ProfileRepository
import org.koin.dsl.module

val dataModule = module {
    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }
}