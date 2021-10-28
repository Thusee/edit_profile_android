package com.thusee.profile.views.editprofile.event

import com.thusee.profile.data.response.UpdateProfileResponse

sealed class EditProfileUiViewState {
    object ShowProgressBar: EditProfileUiViewState()
    object HideProgressBar: EditProfileUiViewState()
    data class ErrorHandle(val e: Throwable): EditProfileUiViewState()
    data class Response(val response: UpdateProfileResponse): EditProfileUiViewState()
}