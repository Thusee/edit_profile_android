package com.thusee.profile.data.response

import com.squareup.moshi.Json

data class MultiChoiceResponse(
    @field:Json(name = "code") val code: Int = 0,
    @field:Json(name = "status") val status: String = "",
    @field:Json(name = "message") val message: String = "",
    @field:Json(name = "data") val data: MultiChoiceData
)

data class MultiChoiceData(
    @field:Json(name = "ethnicity") val ethnicity: List<KeyValue> = emptyList(),
    @field:Json(name = "figure") val figure: List<KeyValue> = emptyList(),
    @field:Json(name = "gender") val gender: List<KeyValue> = emptyList(),
    @field:Json(name = "maritalStatus") val maritalStatus: List<KeyValue> = emptyList(),
    @field:Json(name = "religion") val religion: List<KeyValue> = emptyList(),
)

data class KeyValue(
    @field:Json(name = "id") val id: String = "",
    @field:Json(name = "name") val name: String = ""
)