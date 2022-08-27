package com.bignerdranch.android.testtask.main_flow.feature_profile.domain

import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.GetProfilePhotoUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.LogoutUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.RemoveProfilePhotoUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.SetProfilePhotoUseCase
import org.koin.dsl.module

val domainModule = module {
    single {
        GetProfilePhotoUseCase(get())
    }

    single {
        LogoutUseCase(get())
    }

    single {
        RemoveProfilePhotoUseCase(get())
    }

    single {
        SetProfilePhotoUseCase(get())
    }
}