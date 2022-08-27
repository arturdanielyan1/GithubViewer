package com.bignerdranch.android.testtask.main_flow.feature_profile.presentation

import com.bignerdranch.android.testtask.main_flow.feature_profile.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        ProfileViewModel(get(), get(), get(), get())
    }
}