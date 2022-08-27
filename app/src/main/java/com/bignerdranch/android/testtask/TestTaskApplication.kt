package com.bignerdranch.android.testtask

import android.app.Application
import com.bignerdranch.android.testtask.core.coreModule
import com.bignerdranch.android.testtask.feature_login.loginModule
import com.bignerdranch.android.testtask.main_flow.feature_all_users.allUsersModule
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.myReposModule
import com.bignerdranch.android.testtask.main_flow.feature_profile.profileModule
import com.bignerdranch.android.testtask.user_details.userDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestTaskApplication)
            modules(
                coreModule + loginModule + myReposModule + allUsersModule + userDetailsModule
                + profileModule
            )
        }
    }
}