package com.bignerdranch.android.testtask.feature_login.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.testtask.core.SingleLiveData
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.feature_login.domain.usecases.IsLoggedInUseCase
import com.bignerdranch.android.testtask.feature_login.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loggedInUseCase: IsLoggedInUseCase
) : ViewModel() {

    val isLoginSucceed: SingleLiveData<Unit> = SingleLiveData()

    var isLoadingS by mutableStateOf(false)

    val isFailed: SingleLiveData<String> = SingleLiveData("")

    fun authenticate(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(token).collect { result: Result<Unit> ->
                when(result) {
                    is Result.Success -> {
                        isLoginSucceed.postValue(Unit)
                    }
                    is Result.Loading -> { isLoadingS = true }
                    is Result.Error -> {
                        isLoadingS = false
                        isFailed.postValue(result.message!!)
                    }
                }
            }
        }
    }

    fun notifyIfLoggedIn() {
        viewModelScope.launch {
            if(loggedInUseCase().data == true) {
                isLoginSucceed.postValue(Unit)
            }
        }
    }
}