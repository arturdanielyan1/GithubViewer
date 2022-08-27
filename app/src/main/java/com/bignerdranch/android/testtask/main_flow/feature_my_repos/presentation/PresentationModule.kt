package com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation

import com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation.my_repos.MyReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MyReposViewModel(get())
    }
}