package com.thusee.profile.views.editprofile

import androidx.lifecycle.Observer

class MultiChoiceObserver(private val editProfileView: EditProfileView):
    Observer<MultiChoiceLoadEvent> {

    override fun onChanged(event: MultiChoiceLoadEvent?) {
        when (event) {
            is MultiChoiceLoadEvent.LoadMultiChoiceData -> editProfileView.changeState(
                EditProfileView.State.DisplayMultiChoiceData(event.response)
            )
        }
    }
}