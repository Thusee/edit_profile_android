package com.thusee.profile.data.request

import com.squareup.moshi.Json

data class UpdateProfileRequest (@field:Json(name = "displayName") val displayName: String = "",
                                  @field:Json(name = "realName") val realName: String = "",
                                  @field:Json(name = "profilePic") val profilePic: String = "",
                                  @field:Json(name = "birthday") val birthday: String = "",
                                  @field:Json(name = "gender") val gender: Int = 0,
                                  @field:Json(name = "ethnicity") val ethnicity: Int = 0,
                                  @field:Json(name = "religion") val religion: String = "",
                                  @field:Json(name = "height") val height: Int = 0,
                                  @field:Json(name = "figure") val figure: String = "",
                                  @field:Json(name = "maritalStatus") val maritalStatus: String = "",
                                  @field:Json(name = "occuapion") val occuapion: String = "",
                                  @field:Json(name = "aboutMe") val aboutMe: String = "",
                                  @field:Json(name = "location") val location: Location? = null,
)

data class Location(@field:Json(name = "city") val city: String = "",
                    @field:Json(name = "lat") val lat: String = "",
                    @field:Json(name = "lon") val lon: String = "")