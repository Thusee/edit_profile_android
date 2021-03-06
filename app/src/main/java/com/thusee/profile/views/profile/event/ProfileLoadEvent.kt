package com.thusee.profile.views.profile.event

import com.thusee.profile.data.response.Data

sealed class ProfileLoadEvent {
    data class DisplayProfileData(var data: Data): ProfileLoadEvent()
}