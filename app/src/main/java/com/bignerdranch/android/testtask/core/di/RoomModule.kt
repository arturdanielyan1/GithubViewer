package com.bignerdranch.android.testtask.core.di

import androidx.room.Room
import com.bignerdranch.android.testtask.core.data.room.MyDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
//    single {
//        Room.databaseBuilder(
//            androidApplication().applicationContext,
//            AllUsersDb::class.java,
//            "my_repos_cache_database"
//        ).build()
//    }

    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            MyDb::class.java,
            "my_repos_cache_database"
        ).build()
    }
}