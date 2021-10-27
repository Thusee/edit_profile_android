package com.thusee.profile.views.editprofile

import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.data.response.MultiChoiceData

sealed class MultiChoiceLoadEvent {
    class LoadMultiChoiceData(val response: MutableMap<String, List<KeyValue>>): MultiChoiceLoadEvent()
}