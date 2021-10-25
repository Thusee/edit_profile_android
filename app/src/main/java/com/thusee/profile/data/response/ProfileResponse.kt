package com.thusee.profile.data.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(
    @field:Json(name = "code") val code: Int = 0,
    @field:Json(name = "message") val message: String = "",
    @field:Json(name = "data") val data: Data
): Parcelable

@Parcelize
data class Data(
    @field:Json(name = "aboutMe") val aboutMe: String = "",
    @field:Json(name = "birthday") val birthday: String = "",
    @field:Json(name = "displayName") val displayName: String = "",
    @field:Json(name = "ethnicity") val ethnicity: String = "",
    @field:Json(name = "figure") val figure: String = "",
    @field:Json(name = "gender") val gender: String = "",
    @field:Json(name = "height") val height: Int = 0,
    @field:Json(name = "location") val location: Location? = null,
    @field:Json(name = "maritalStatus") val maritalStatus: String = "",
    @field:Json(name = "occuapion") val occuapion: String = "",
    @field:Json(name = "profilePic") val profilePic: String = "",
    @field:Json(name = "realName") val realName: String = "",
    @field:Json(name = "religion") val religion: String = "",
): Parcelable

@Parcelize
data class Location(
    @field:Json(name = "city") val city: String = "",
    @field:Json(name = "lat") val lat: String = "",
    @field:Json(name = "lon") val lon: String = "",
): Parcelable