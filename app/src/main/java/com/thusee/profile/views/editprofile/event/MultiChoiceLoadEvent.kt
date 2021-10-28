package com.thusee.profile.views.editprofile.event

import com.thusee.profile.data.request.Cities
import com.thusee.profile.data.response.KeyValue

sealed class MultiChoiceLoadEvent {
    class LoadMultiChoiceData(val response: MutableMap<String, List<KeyValue>>, val cities: List<Cities>): MultiChoiceLoadEvent()
}