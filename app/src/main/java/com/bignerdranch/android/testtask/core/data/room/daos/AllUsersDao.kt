package com.bignerdranch.android.testtask.core.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.testtask.core.LOAD_USERS_BY
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.core.data.room.db_scheams.AllUsersDBSchema

@Dao
interface AllUsersDao {

    @Query("SELECT * FROM ${AllUsersDBSchema.TABLE_NAME} WHERE id > :fromId ORDER BY id LIMIT $LOAD_USERS_BY")
    suspend fun getUsers(fromId: Int): List<UserData>

    @Query("SELECT COUNT(*) FROM ${AllUsersDBSchema.TABLE_NAME}")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserData)

}