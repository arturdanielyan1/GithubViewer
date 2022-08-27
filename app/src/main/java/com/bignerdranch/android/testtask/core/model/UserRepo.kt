package com.bignerdranch.android.testtask.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.testtask.core.data.room.db_scheams.MyReposDBSchema
import com.google.gson.annotations.SerializedName

@Entity(tableName = MyReposDBSchema.TABLE_NAME)
open class UserRepo (
    @PrimaryKey @SerializedName("id") val rowId: Int = 0,
    @ColumnInfo(name = MyReposDBSchema.COL_REPO_NAME) val name: String = "",
    @ColumnInfo(name = MyReposDBSchema.COL_REPO_LANG) val language: String = "")
{

    class LoadingRepo : UserRepo()
}