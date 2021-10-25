package com.thusee.profile.views.profile.observer

import androidx.lifecycle.Observer
import com.thusee.profile.views.profile.ProfileView
import com.thusee.profile.viewstate.UiViewState

class UiViewObserver(private val profileView: ProfileView): Observer<UiViewState> {

    override fun onChanged(event: UiViewState?) {
        when (event){
            is UiViewState.ShowProgressBar -> profileView.changeUiState(ProfileView.UiState.ShowProgressBar)
            is UiViewState.HideProgressBar -> profileView.changeUiState(ProfileView.UiState.HideProgressBar)
            is UiViewState.ErrorHandle -> profileView.changeUiState(
                ProfileView.UiState.ErrorHandle(
                    event.e
                )
            )
        }
    }
}