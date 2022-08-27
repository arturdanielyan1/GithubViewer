package com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation


import com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users.AllUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        AllUsersViewModel(get())
    }
}