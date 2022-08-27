package com.bignerdranch.android.testtask.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.testtask.core.data.room.daos.AllUsersDao
import com.bignerdranch.android.testtask.core.data.room.daos.MyReposDao
import com.bignerdranch.android.testtask.core.data.room.db_scheams.BitmapConverter
import com.bignerdranch.android.testtask.core.data.room.db_scheams.MyReposDBSchema
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData

@Database(entities = [UserRepo::class, UserData::class], version = MyReposDBSchema.VERSION)
@TypeConverters(BitmapConverter::class)
abstract class MyDb : RoomDatabase() {

    abstract fun myReposDao(): MyReposDao
    abstract fun allUsersDao(): AllUsersDao
}