package com.bignerdranch.android.testtask.user_details

import com.bignerdranch.android.testtask.user_details.data.dataModule
import com.bignerdranch.android.testtask.user_details.domain.domainModule
import com.bignerdranch.android.testtask.user_details.presentation.presentationModule


val userDetailsModule = dataModule + domainModule + presentationModule