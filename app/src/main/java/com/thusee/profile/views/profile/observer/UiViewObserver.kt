package com.thusee.profile.views.profile.observer

import androidx.lifecycle.Observer
import com.thusee.profile.views.profile.ProfileView
import com.thusee.profile.views.profile.event.ProfileUiViewState

class UiViewObserver(private val profileView: ProfileView): Observer<ProfileUiViewState> {

    override fun onChanged(event: ProfileUiViewState?) {
        when (event){
            is ProfileUiViewState.ShowProgressBar -> profileView.changeUiState(ProfileView.UiState.ShowProgressBar)
            is ProfileUiViewState.HideProgressBar -> profileView.changeUiState(ProfileView.UiState.HideProgressBar)
            is ProfileUiViewState.ErrorHandle -> profileView.changeUiState(
                ProfileView.UiState.ErrorHandle(
                    event.e
                )
            )
        }
    }
}