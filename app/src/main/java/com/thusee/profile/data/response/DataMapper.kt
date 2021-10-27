package com.thusee.profile.data.response

import com.thusee.profile.util.ETHNICITY
import com.thusee.profile.util.FIGURE
import com.thusee.profile.util.GENDER
import com.thusee.profile.util.MARITAL_STATUS
import com.thusee.profile.util.RELIGION

class DataMapper {

    fun toMap(data: MultiChoiceData): MutableMap<String, List<KeyValue>> {
        val map = mutableMapOf<String, List<KeyValue>>()
        map[ETHNICITY] = data.ethnicity
        map[FIGURE] = data.figure
        map[GENDER] = data.gender
        map[MARITAL_STATUS] = data.maritalStatus
        map[RELIGION] = data.religion

        return map
    }
}