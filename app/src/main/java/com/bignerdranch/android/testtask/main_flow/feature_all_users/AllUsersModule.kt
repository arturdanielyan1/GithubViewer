package com.bignerdranch.android.testtask.main_flow.feature_all_users

import com.bignerdranch.android.testtask.main_flow.feature_all_users.data.dataModule
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.domainModule
import com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.presentationModule

val allUsersModule = dataModule + domainModule + presentationModule