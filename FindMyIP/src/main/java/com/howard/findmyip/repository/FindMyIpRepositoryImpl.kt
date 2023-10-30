package com.howard.findmyip.repository

import com.howard.findmyip.model.FindMyIPUiState
import com.howard.findmyip.model.FindMyIpApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FindMyIpRepositoryImpl @Inject constructor(private val apiDetails: FindMyIpApi): FindMyIpRepo {
    override suspend fun getIpWithRateLimit(): Flow<FindMyIPUiState> {
        return flow {
            // Handles too many requests being sent
            val rateLimitDelay = 5000L

            emit(FindMyIPUiState.Loading())
            // Wait for the specified time from above before making the request
            delay(rateLimitDelay)

            val response = apiDetails.getMyIp()

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