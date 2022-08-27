package com.bignerdranch.android.testtask.user_details.presentation

import com.bignerdranch.android.testtask.user_details.presentation.user_details.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        UserDetailsViewModel(get())
    }
}