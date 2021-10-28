package com.thusee.profile.views.editprofile.observer

import androidx.lifecycle.Observer
import com.thusee.profile.views.editprofile.EditProfileView
import com.thusee.profile.views.editprofile.event.EditProfileUiViewState

class EditProfileViewObserver(private val editProfileView: EditProfileView):
    Observer<EditProfileUiViewState> {

    override fun onChanged(event: EditProfileUiViewState?) {
        when (event) {
            is EditProfileUiViewState.ShowProgressBar -> editProfileView.changeUiState(
                EditProfileView.UiState.ShowProgressBar
            )
            is EditProfileUiViewState.HideProgressBar -> editProfileView.changeUiState(
                EditProfileView.UiState.HideProgressBar
            )
            is EditProfileUiViewState.ErrorHandle -> editProfileView.changeUiState(
                EditProfileView.UiState.ErrorHandle(event.e)
            )
            is EditProfileUiViewState.Response -> editProfileView.changeUiState(
                EditProfileView.UiState.Response(event.response)
            )
        }
    }
}