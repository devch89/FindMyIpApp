package com.howard.findmyip.model

sealed class FindMyIPUiState {
    data class Loading(val isLoading: Boolean = true) : FindMyIPUiState()
    data class Success(val isSuccess: FindMyIpResponse) : FindMyIPUiState()
    data class Error(val message: String) : FindMyIPUiState()
    object Initial: FindMyIPUiState()
}