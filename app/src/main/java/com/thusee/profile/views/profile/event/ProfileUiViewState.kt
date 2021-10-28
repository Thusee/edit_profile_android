package com.thusee.profile.views.profile.event

sealed class ProfileUiViewState {
    object ShowProgressBar: ProfileUiViewState()
    object HideProgressBar: ProfileUiViewState()
    data class ErrorHandle(val e: Throwable) : ProfileUiViewState()
}