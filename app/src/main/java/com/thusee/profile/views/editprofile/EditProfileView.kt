package com.thusee.profile.views.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.thusee.profile.data.response.Data
import com.thusee.profile.data.response.KeyValue

interface EditProfileView {
    fun inflate(inflater: LayoutInflater, savedInstanceState: Bundle?): View?
    fun changeState(state: State)
    fun updateUI(data: Data)

    sealed class State{
        class DisplayMultiChoiceData(val data: MutableMap<String, List<KeyValue>>): State()
    }

}