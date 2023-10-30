package com.howard.findmyip.repository

import com.howard.findmyip.model.FindMyIPUiState
import kotlinx.coroutines.flow.Flow

interface FindMyIpRepo {
    suspend fun getIpWithRateLimit(): Flow<FindMyIPUiState>
}