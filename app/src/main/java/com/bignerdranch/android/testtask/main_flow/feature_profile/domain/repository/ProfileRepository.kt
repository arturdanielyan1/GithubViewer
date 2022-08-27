package com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri

interface ProfileRepository {

    suspend fun getProfilePhoto(): Bitmap?

    suspend fun logout()

    suspend fun removeProfilePhoto()

    suspend fun setProfilePhoto(contentResolver: ContentResolver, imageUri: Uri)
}