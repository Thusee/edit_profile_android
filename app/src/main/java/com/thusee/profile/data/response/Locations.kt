package com.thusee.profile.data.response

import com.squareup.moshi.Json
import com.thusee.profile.data.request.Cities

data class Locations(@field:Json(name = "cities") val cities: List<Cities> = emptyList())

