package com.thusee.profile.data.network

import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.ProfileResponse
import com.thusee.profile.data.response.UpdateProfileResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/profile")
    suspend fun fetchProfileData(): ProfileResponse

    @POST("/addProfile")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): UpdateProfileResponse

}