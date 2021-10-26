package com.thusee.profile.usecase

import com.thusee.profile.data.network.ApiService
import com.thusee.profile.data.response.MultiChoiceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent

class FetchMultiChoiceRepo(private val apiService: ApiService): KoinComponent {

    suspend fun getMultiChoiceData(): Flow<MultiChoiceResponse> {
        return flow {
            emit(apiService.getMultiChoiceData())
        }
    }
}