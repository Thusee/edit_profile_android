package com.thusee.profile.views.editprofile.observer

import androidx.lifecycle.Observer
import com.thusee.profile.views.editprofile.EditProfileView
import com.thusee.profile.views.editprofile.event.MultiChoiceLoadEvent

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