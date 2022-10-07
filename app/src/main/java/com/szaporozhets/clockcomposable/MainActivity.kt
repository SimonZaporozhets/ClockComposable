package com.szaporozhets.clockcomposable

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val milliseconds = remember {
                    System.currentTimeMillis()
                }
                var seconds by remember {
                    mutableStateOf((milliseconds / 1000f) % 60f)
                }
                var minutes by remember {
                    mutableStateOf(((milliseconds / 1000f) / 60) % 60f)
                }
                var hours by remember {
                    mutableStateOf((milliseconds / 1000f) / 3600f + 2f)
                }
                LaunchedEffect(key1 = seconds) {
                    delay(1000L)
                    minutes += 1f / 60f
                    hours += 1f / (60f * 12f)
                    seconds += 1f
                }
                Clock(
                    seconds = seconds,
                    minutes = minutes,
                    hours = hours
                )
            }
        }
    }

}