package com.thusee.profile.views.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.thusee.profile.data.request.Cities
import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.Data
import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.data.response.UpdateProfileResponse

interface EditProfileView {
    fun inflate(inflater: LayoutInflater, savedInstanceState: Bundle?): View?
    fun changeState(state: State)
    fun updateUI(data: Data)
    fun getUpdateProfileRequest(result: MutableMap<String, List<KeyValue>>, cities: List<Cities>): UpdateProfileRequest
    fun setCallBack(listener: SubmitButtonClickListener)
    fun showPopUpDialog()
    fun validateTextField(): Boolean
    fun changeUiState(state: UiState)


    sealed class State{
        class DisplayMultiChoiceData(val data: MutableMap<String, List<KeyValue>>, val cities: List<Cities>): State()
    }

    sealed class UiState{
        object ShowProgressBar: UiState()
        object HideProgressBar: UiState()
        data class ErrorHandle(val e: Throwable): UiState()
        data class Response(val response: UpdateProfileResponse): UiState()
    }

}