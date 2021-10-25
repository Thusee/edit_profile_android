package com.thusee.profile.usecase

import com.thusee.profile.data.network.ApiService
import com.thusee.profile.data.response.ProfileResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class FetchProfileRepo: KoinComponent {

    private val apiService: ApiService by inject()

    suspend fun fetchProfileData(): Flow<ProfileResponse> {
        return flow {
            emit(apiService.fetchProfileData())
        }
    }

}