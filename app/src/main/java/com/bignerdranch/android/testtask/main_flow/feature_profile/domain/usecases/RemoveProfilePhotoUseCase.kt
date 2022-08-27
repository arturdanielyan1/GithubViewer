package com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases

import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.repository.ProfileRepository

class RemoveProfilePhotoUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke() =
        profileRepository.removeProfilePhoto()
}