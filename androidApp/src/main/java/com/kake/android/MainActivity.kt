package com.kake.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.kake.base.Testing
import com.kake.android.ui.theme.KMPDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMPDemoTheme {
                Column {
                    Text(text = Testing().me)
                }
//                MainScreen()
            }
        }
    }
}