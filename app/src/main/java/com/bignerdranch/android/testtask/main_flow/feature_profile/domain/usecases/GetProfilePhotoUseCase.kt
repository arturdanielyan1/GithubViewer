package com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases

import android.graphics.Bitmap
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository.ProfileRepository

class GetProfilePhotoUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(): Bitmap? =
        profileRepository.getProfilePhoto()
}