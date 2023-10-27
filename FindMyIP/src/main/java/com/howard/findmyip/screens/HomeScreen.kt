package com.howard.findmyip.screens
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.howard.findmyip.model.FindMyIPUiState
import com.howard.findmyip.model.FindMyIpResponse
import com.howard.findmyip.viewmodel.FindMyIpViewModel


@Composable
fun DisplayIp(viewModel: FindMyIpViewModel) {
    val ipUiState = viewModel.ipUiState.collectAsState().value
    var progress by remember { mutableStateOf(0.5f) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (ipUiState) {
            is FindMyIPUiState.Loading -> {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            }
            is FindMyIPUiState.Success -> {
                DisplaySuccess(response = ipUiState.isSuccess)
            }
            is FindMyIPUiState.Error -> DisplayError(ipUiState.message)
            else -> {}
        }
    }
}

@Composable
fun DisplayError(errorMessage: String) {
    Text(text = errorMessage, color = Color.Red)
}

@Composable
fun DisplaySuccess(response: FindMyIpResponse) {
    Column {
        Text(text = "Your City: ${response.city}")
        Text(text = "Your Country: ${response.country_name}")
        Text(text = "Your Region: ${response.region}")
    }
}