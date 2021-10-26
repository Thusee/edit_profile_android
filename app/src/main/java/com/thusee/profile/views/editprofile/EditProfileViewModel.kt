package com.thusee.profile.views.editprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.profile.data.response.MultiChoiceResponse
import com.thusee.profile.usecase.FetchMultiChoiceRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class EditProfileViewModel(private val fetchMultiChoiceRepo: FetchMultiChoiceRepo): ViewModel(), KoinComponent {

    var multiChoiceLiveData = MutableLiveData<MultiChoiceResponse>()

    private var multiChoiceResponse: MultiChoiceResponse? = null

    init {
        fetchMultiChoiceData()
    }

    fun fetchMultiChoiceData() {
        viewModelScope.launch {
            fetchMultiChoiceRepo.getMultiChoiceData().catch { ex ->

            }.collect {
                multiChoiceResponse = it
                multiChoiceLiveData.value = it
            }
        }
    }

    fun getMultiChoiceData(): MultiChoiceResponse? {
        return multiChoiceResponse
    }
}