package com.thusee.profile.usecase

import com.thusee.profile.data.network.ApiService
import com.thusee.profile.data.response.DataMapper
import com.thusee.profile.data.response.KeyValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent

class FetchMultiChoiceRepo(private val apiService: ApiService): KoinComponent {

    suspend fun getMultiChoiceData(): Flow<MutableMap<String, List<KeyValue>>> {
        return flow {

            val map = DataMapper().toMap(apiService.getMultiChoiceData().data)
            emit(map)
        }
    }

}