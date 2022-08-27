package com.bignerdranch.android.testtask.main_flow.feature_profile.presentation.profile

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.GetProfilePhotoUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.LogoutUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.RemoveProfilePhotoUseCase
import com.bignerdranch.android.testtask.main_flow.feature_profile.domain.usecases.SetProfilePhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfilePhotoUseCase: GetProfilePhotoUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val removeProfilePhotoUseCase: RemoveProfilePhotoUseCase,
    private val setProfilePhotoUseCase: SetProfilePhotoUseCase
) : ViewModel() {

    val profilePhoto: MutableLiveData<Bitmap?> = MutableLiveData()


    fun getProfilePhoto() {
        viewModelScope.launch {
            getProfilePhotoUseCase()?.apply {
                profilePhoto.setValue(this)
            }
        }
    }

    fun removeProfilePhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            removeProfilePhotoUseCase()
        }
    }

    fun setProfilePhoto(contentResolver: ContentResolver, imageUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            setProfilePhotoUseCase(contentResolver, imageUri)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
        }
    }
}