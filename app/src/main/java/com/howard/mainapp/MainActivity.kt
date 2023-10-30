package com.howard.mainapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.howard.findmyip.FindMyIpActivity
import com.howard.mainapp.ui.theme.MainAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startActivity(
                        Intent().apply {
                            action = Intent.ACTION_VIEW
                            setClass(this@MainActivity,
                                FindMyIpActivity::class.java)
                        }
                    )
                }
            }
        }
    }
}

