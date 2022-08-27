package com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.testtask.core.SingleLiveData
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllUsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val usersList: MutableLiveData<List<UserData>> = MutableLiveData(emptyList())

    var isLoading: SingleLiveData<Boolean> = SingleLiveData(false)

    val hasFailed: SingleLiveData<String> = SingleLiveData(null)

    private var loaded = false


    init {
        loadMore(0)
    }

    fun loadMore(since: Int) {
        if (isLoading.value == true || loaded) return
        viewModelScope.launch(Dispatchers.IO) {
            getUsersUseCase(since).collect { result ->
                when(result) {
                    is Result.Success -> {
                        if (result.data?.isEmpty() == true) loaded = true
                        isLoading.postValue(false)
                        usersList.postValue(result.data!!)
                    }
                    is Result.Loading -> {
                        usersList.postValue(usersList.value!! + listOf(UserData.Loading()))
                        isLoading.postValue(true)
                    }
                    is Result.Error -> {
                        isLoading.postValue(false)
                        if(hasFailed.value == null)
                        hasFailed.postValue(result.message!!)
                    }
                }
            }
        }
    }
}