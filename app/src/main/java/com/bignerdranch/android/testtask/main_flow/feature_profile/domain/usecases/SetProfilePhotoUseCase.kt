package com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases

import android.content.ContentResolver
import android.net.Uri
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository.ProfileRepository

class SetProfilePhotoUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(contentResolver: ContentResolver, imageUri: Uri) =
        profileRepository.setProfilePhoto(contentResolver, imageUri)
}