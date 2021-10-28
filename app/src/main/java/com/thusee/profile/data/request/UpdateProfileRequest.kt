package com.thusee.profile.data.request

import com.squareup.moshi.Json

data class UpdateProfileRequest (@field:Json(name = "displayName") var displayName: String = "",
                                 @field:Json(name = "realName") var realName: String = "",
                                 @field:Json(name = "profilePic") var profilePic: String = "",
                                 @field:Json(name = "birthday") var birthday: String = "",
                                 @field:Json(name = "gender") var gender: String = "",
                                 @field:Json(name = "ethnicity") var ethnicity: String = "",
                                 @field:Json(name = "religion") var religion: String = "",
                                 @field:Json(name = "height") var height: Int = 0,
                                 @field:Json(name = "figure") var figure: String = "",
                                 @field:Json(name = "maritalStatus") var maritalStatus: String = "",
                                 @field:Json(name = "occuapion") var occuapion: String = "",
                                 @field:Json(name = "aboutMe") var aboutMe: String = "",
                                 @field:Json(name = "location") var location: Cities? = null,
)
data class Cities(
    @field:Json(name = "city") val city: String = "",
    @field:Json(name = "lat") val lat: String = "",
    @field:Json(name = "lon") val lon: String = ""
)