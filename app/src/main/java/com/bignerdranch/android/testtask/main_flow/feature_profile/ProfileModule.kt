package com.bignerdranch.android.testtask.main_flow.feature_profile

import com.bignerdranch.android.testtask.main_flow.feature_profile.data.dataModule
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.domainModule
import com.bignerdranch.android.testtask.main_flow.feature_profile.presentation.presentationModule

val profileModule = dataModule + domainModule + presentationModule