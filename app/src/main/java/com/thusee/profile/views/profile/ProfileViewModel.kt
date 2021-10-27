package com.thusee.profile.views.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.profile.data.response.Data
import com.thusee.profile.usecase.FetchProfileRepo
import com.thusee.profile.viewstate.UiViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class ProfileViewModel(
    private val fetchProfileRepo: FetchProfileRepo
): ViewModel(), KoinComponent {

    val profileLiveData = MutableLiveData<ProfileLoadEvent>()
    val viewState = MutableLiveData<UiViewState>()

    private var data: Data? = null

    fun fetchProfileData() {
        viewModelScope.launch {
            fetchProfileRepo.fetchProfileData().onStart {
                viewState.value = UiViewState.ShowProgressBar
            }.catch { ex ->
                viewState.value = UiViewState.HideProgressBar
                viewState.value = UiViewState.ErrorHandle(ex)
            }.collect { response ->
                viewState.value = UiViewState.HideProgressBar
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