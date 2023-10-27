package com.howard.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.howard.findmyip.repository.FindMyIpRepo
import com.howard.findmyip.screens.DisplayIp
import com.howard.findmyip.ui.theme.FindMyIpTheme
import com.howard.findmyip.viewmodel.FindMyIpViewModel
import com.howard.findmyip.viewmodel.FindMyIpViewModelFactory

class FindMyIpActivity : ComponentActivity() {

    private val viewModel: FindMyIpViewModel by viewModels{
        FindMyIpViewModelFactory(FindMyIpRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIp(viewModel)
                }
            }
        }
    }
}
