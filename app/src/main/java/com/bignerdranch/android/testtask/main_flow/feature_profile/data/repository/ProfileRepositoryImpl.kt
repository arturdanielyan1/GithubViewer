package com.bignerdranch.android.testtask.main_flow.feature_profile.data.repository

import android.content.ContentResolver
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import com.bignerdranch.android.testtask.core.data.PREF_LOGGED_IN
import com.bignerdranch.android.testtask.core.data.PREF_PROFILE_PHOTO_URI
import com.bignerdranch.android.testtask.core.data.PREF_TOKEN
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository.ProfileRepository
import java.io.ByteArrayOutputStream

class ProfileRepositoryImpl(
    private val sharedPref: SharedPreferences
) : ProfileRepository {


    override suspend fun getProfilePhoto(): Bitmap? {
        val imageString = sharedPref.getString(PREF_PROFILE_PHOTO_URI, "noDataFound")

        if (imageString == "noDataFound") return null
        val byteArray = Base64.decode(imageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override suspend fun logout() {
        sharedPref.edit()
            .putString(PREF_TOKEN, "")
            .apply()
    }

    override suspend fun removeProfilePhoto() {
        sharedPref.edit()
            .remove(PREF_PROFILE_PHOTO_URI)
            .apply()
    }

    override suspend fun setProfilePhoto(contentResolver: ContentResolver, imageUri: Uri) {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, imageUri))
        } else {
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        }

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        val imageString = Base64.encodeToString(byteArray, Base64.DEFAULT)

        sharedPref.edit()
            .putString(PREF_PROFILE_PHOTO_URI, imageString)
            .apply()
    }
}