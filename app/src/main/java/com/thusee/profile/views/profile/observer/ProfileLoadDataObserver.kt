package com.thusee.profile.views.profile.observer

import androidx.lifecycle.Observer
import com.thusee.profile.views.profile.ProfileLoadEvent
import com.thusee.profile.views.profile.ProfileView

class ProfileLoadDataObserver(private val profileView: ProfileView): Observer<ProfileLoadEvent> {
    override fun onChanged(event: ProfileLoadEvent?) {
        when (event){
            is ProfileLoadEvent.DisplayProfileData -> profileView.changeState(
                ProfileView.State.DisplayProfileData(
                    event.data
                )
            )
        }
    }
}