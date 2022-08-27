package com.bignerdranch.android.testtask.core.data.room.db_scheams


class MyReposDBSchema {
    companion object {
        const val TABLE_NAME = "my_repos_cache"
        const val VERSION = 1

        const val COL_REPO_NAME = "repo_name"
        const val COL_REPO_LANG = "repo_main_language"
    }
}