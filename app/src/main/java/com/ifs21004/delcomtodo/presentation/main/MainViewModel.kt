package com.ifs21004.delcomtodo.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ifs21004.delcomtodo.ViewModelFactory
import com.ifs21004.delcomtodo.data.pref.UserModel
import com.ifs21004.delcomtodo.data.repository.AuthRepository
import kotlinx.coroutines.launch
class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return authRepository.getSession().asLiveData()
    }
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: MainViewModel? = null
        fun getInstance(
            authRepository: AuthRepository
        ): MainViewModel {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = MainViewModel(
                    authRepository
                )
            }
            return INSTANCE as MainViewModel
        }
    }
}
