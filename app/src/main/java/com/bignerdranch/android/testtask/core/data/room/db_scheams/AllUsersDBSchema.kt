package com.bignerdranch.android.testtask.core.data.room.db_scheams

class AllUsersDBSchema {
    companion object {
        const val TABLE_NAME = "all_users_cache"

        const val COL_LOGIN = "userLogin"
        const val COL_REPOS_URL = "reposUrl"
        const val COL_PROFILE_BITMAP = "profilePictureBitmap"
    }
}