package com.bignerdranch.android.testtask.core

import com.bignerdranch.android.testtask.core.di.retrofitModule
import com.bignerdranch.android.testtask.core.di.roomModule
import com.bignerdranch.android.testtask.core.di.sharedPreferencesModule

val coreModule = retrofitModule + sharedPreferencesModule + roomModule