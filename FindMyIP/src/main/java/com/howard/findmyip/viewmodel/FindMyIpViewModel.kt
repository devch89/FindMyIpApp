package com.howard.findmyip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.howard.findmyip.model.FindMyIPUiState
import com.howard.findmyip.repository.FindMyIpRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FindMyIpViewModel (private val repository: FindMyIpRepo) : ViewModel() {
    private val _ipUiState = MutableStateFlow<FindMyIPUiState>(FindMyIPUiState.Initial)
    val ipUiState: StateFlow<FindMyIPUiState> get() = _ipUiState

    init {
        getIp()
    }

    private fun getIp(retryCount: Int = 0) {
        viewModelScope.launch {
            repository.getIpWithRateLimit().collect {
                _ipUiState.value = it
            }
        }
    }
}

class FindMyIpViewModelFactory(private val repository: FindMyIpRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FindMyIpViewModel::class.java)) {
            return FindMyIpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}