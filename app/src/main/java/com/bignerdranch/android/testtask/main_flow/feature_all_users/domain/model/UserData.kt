package com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.bignerdranch.android.testtask.core.data.room.db_scheams.AllUsersDBSchema
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = AllUsersDBSchema.TABLE_NAME)
open class UserData (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = -1,
    @ColumnInfo(name = AllUsersDBSchema.COL_LOGIN) val login: String = "",
    @ColumnInfo(name = AllUsersDBSchema.COL_REPOS_URL) @SerializedName("repos_url") val reposUrl: String = "",
) : Serializable
{

    @ColumnInfo(name = AllUsersDBSchema.COL_PROFILE_BITMAP) var avatarBitmap: Bitmap? = null

    @Ignore @SerializedName("avatar_url") val avatarUrl: String = ""

    class Loading : UserData()
}