package com.bignerdranch.android.testtask.feature_login

import com.bignerdranch.android.testtask.feature_login.data.dataModule
import com.bignerdranch.android.testtask.feature_login.domain.domainModule
import com.bignerdranch.android.testtask.feature_login.presentation.presentationModule

val loginModule = dataModule + domainModule + presentationModule