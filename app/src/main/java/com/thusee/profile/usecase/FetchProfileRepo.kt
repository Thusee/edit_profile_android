package com.thusee.profile.usecase

import androidx.lifecycle.MutableLiveData
import com.thusee.profile.data.network.ApiService
import com.thusee.profile.views.profile.ProfileLoadEvent
import com.thusee.profile.viewstate.UiViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class FetchProfileRepo: KoinComponent {

    private val apiService: ApiService by inject()

    suspend fun fetchProfileData(
        profileLiveData: MutableLiveData<ProfileLoadEvent>,
        viewState: MutableLiveData<UiViewState>
    ) {
        flow {
            try {
                emit(apiService.fetchProfileData())
            } catch (e: Throwable) {
                viewState.value = UiViewState.HideProgressBar
                viewState.value = UiViewState.ErrorHandle(e)
            }
        }.onStart {
            viewState.value = UiViewState.ShowProgressBar

        }.collect {
            Timber.d("fetchProfileData() Data ${it.data}")
            if (it.code == 200) {
                profileLiveData.value = ProfileLoadEvent.DisplayProfileData(it.data)
                viewState.value = UiViewState.HideProgressBar
            }
        }
    }
}