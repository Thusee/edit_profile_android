package com.thusee.profile.views.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.profile.usecase.FetchProfileRepo
import com.thusee.profile.viewstate.UiViewState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileViewModel: ViewModel(), KoinComponent {

    val profileLiveData = MutableLiveData<ProfileLoadEvent>()
    val viewState = MutableLiveData<UiViewState>()

    private val fetchProfileRepo: FetchProfileRepo by inject()

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            fetchProfileRepo.fetchProfileData(profileLiveData, viewState)
        }
    }

}