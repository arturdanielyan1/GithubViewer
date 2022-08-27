package com.bignerdranch.android.testtask.main_flow.feature_my_repos

import com.bignerdranch.android.testtask.main_flow.feature_my_repos.data.dataModule
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.domainModule
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation.presentationModule


val myReposModule = dataModule + domainModule + presentationModule