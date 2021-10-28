package com.thusee.profile.data.response

import com.squareup.moshi.Json

data class UpdateProfileResponse(
    @field:Json(name = "code") val code: Int = 0,
    @field:Json(name = "status") val status: String = "",
    @field:Json(name = "message") val message: String = ""
)