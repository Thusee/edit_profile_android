package com.thusee.profile.views.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.profile.data.response.Data
import com.thusee.profile.usecase.FetchProfileRepo
import com.thusee.profile.views.profile.event.ProfileLoadEvent
import com.thusee.profile.views.profile.event.ProfileUiViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class ProfileViewModel(
    private val fetchProfileRepo: FetchProfileRepo
): ViewModel(), KoinComponent {

    val profileLiveData = MutableLiveData<ProfileLoadEvent>()
    val viewState = MutableLiveData<ProfileUiViewState>()

    private var data: Data? = null

    fun fetchProfileData() {
        viewModelScope.launch {
            fetchProfileRepo.fetchProfileData().onStart {
                viewState.value = ProfileUiViewState.ShowProgressBar
            }.catch { ex ->
                viewState.value = ProfileUiViewState.HideProgressBar
                viewState.value = ProfileUiViewState.ErrorHandle(ex)
            }.collect { response ->
                viewState.value = ProfileUiViewState.HideProgressBar
                if (response.code == 200) {
                    profileLiveData.value = ProfileLoadEvent.DisplayProfileData(response.data)
                    data = response.data
                }
            }
        }
    }

    fun getProfileData(): Data? {
        return data
    }

}