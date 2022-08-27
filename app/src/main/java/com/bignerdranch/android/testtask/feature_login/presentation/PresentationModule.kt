package com.bignerdranch.android.testtask.feature_login.presentation

import com.bignerdranch.android.testtask.feature_login.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
}