package com.thusee.profile.views.profile

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.thusee.profile.data.response.Data

interface ProfileView {

    fun inflate(activity: Activity,  savedInstanceState: Bundle?): View?
    fun changeState(state: State)
    fun changeUiState(state: UiState)
    fun setCallBack(listener: ProfileOnClickListener)
    fun navigateToEditProfile()

    sealed class State{
        class DisplayProfileData(val data: Data): State()
    }

    sealed class UiState {
        object ShowProgressBar: UiState()
        object HideProgressBar: UiState()
        data class ErrorHandle(val e: Throwable): UiState()
    }
}