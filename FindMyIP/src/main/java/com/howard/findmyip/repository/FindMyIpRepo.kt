package com.howard.findmyip.repository

import com.howard.findmyip.model.FindMyIPUiState
import com.howard.findmyip.model.FindMyIpNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FindMyIpRepo {
    suspend fun getIpWithRateLimit(): Flow<FindMyIPUiState> {
        return flow {
            // Handles too many requests being sent
            val rateLimitDelay = 5000L

            emit(FindMyIPUiState.Loading())
            // Wait for the specified time from above before making the request
            delay(rateLimitDelay)

            val response = FindMyIpNetwork.api.getMyIp()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(FindMyIPUiState.Loading(false))
                    emit(FindMyIPUiState.Success(it))
                } ?: kotlin.run {
                    emit(FindMyIPUiState.Loading(false))
                    emit(FindMyIPUiState.Error(response.message()))
                }
            } else {
                emit(FindMyIPUiState.Loading(false))
                emit(FindMyIPUiState.Error(response.message()))
            }
        }
    }
}