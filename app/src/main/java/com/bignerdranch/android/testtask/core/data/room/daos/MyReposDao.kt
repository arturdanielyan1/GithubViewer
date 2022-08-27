package com.bignerdranch.android.testtask.core.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.testtask.core.LOAD_REPOS_BY
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.core.data.room.db_scheams.MyReposDBSchema

@Dao
interface MyReposDao {

    @Query("SELECT * FROM ${MyReposDBSchema.TABLE_NAME} WHERE rowId > :from ORDER BY rowId ASC LIMIT $LOAD_REPOS_BY")
    suspend fun getRepos(from: Int): List<UserRepo>

    @Query("SELECT COUNT(*) FROM ${MyReposDBSchema.TABLE_NAME}")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: UserRepo)
}