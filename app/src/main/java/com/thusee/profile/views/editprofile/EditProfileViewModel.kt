package com.thusee.profile.views.editprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.usecase.FetchMultiChoiceRepo
import com.thusee.profile.usecase.UpdateProfileRepo
import com.thusee.profile.viewstate.UiViewState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class EditProfileViewModel(
    private val fetchMultiChoiceRepo: FetchMultiChoiceRepo,
    private val updateProfileRepo: UpdateProfileRepo
): ViewModel(),
    KoinComponent {

    val viewState = MutableLiveData<UiViewState>()
    var multiChoiceLiveData = MutableLiveData<MultiChoiceLoadEvent>()

    private var data: MutableMap<String, List<KeyValue>>? = null

    init {
        fetchMultiChoiceData()
    }

    fun fetchMultiChoiceData() {
        viewModelScope.launch {
            fetchMultiChoiceRepo.getMultiChoiceData().catch { ex ->

            }.collect {
                data = it
                multiChoiceLiveData.value = MultiChoiceLoadEvent.LoadMultiChoiceData(it)
            }
        }
    }

    fun getMultiChoiceData(): MutableMap<String, List<KeyValue>>? {
        return data
    }

    fun updateProfile() {
        viewModelScope.launch {
            updateProfileRepo.updateProfile(UpdateProfileRequest()).onStart {
                viewState.value = UiViewState.ShowProgressBar
            }.catch { ex ->
                viewState.value = UiViewState.HideProgressBar
            }.collect {
                viewState.value = UiViewState.HideProgressBar

            }
        }
    }
}