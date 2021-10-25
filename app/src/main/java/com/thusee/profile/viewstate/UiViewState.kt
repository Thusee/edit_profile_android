package com.thusee.profile.viewstate

sealed class UiViewState {
    object ShowProgressBar: UiViewState()
    object HideProgressBar: UiViewState()
    data class ErrorHandle(val e: Throwable) : UiViewState()
}