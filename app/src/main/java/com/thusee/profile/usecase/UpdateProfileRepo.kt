package com.thusee.profile.usecase

import com.thusee.profile.data.network.ApiService
import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.UpdateProfileResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent

class UpdateProfileRepo(private val apiService: ApiService): KoinComponent {

    suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest): Flow<UpdateProfileResponse> {
        return flow {
            emit(apiService.updateProfile(updateProfileRequest))
        }
    }
}