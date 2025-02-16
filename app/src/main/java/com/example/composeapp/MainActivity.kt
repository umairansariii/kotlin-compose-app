package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.composeapp.components.BottomNavigationBar
import com.example.composeapp.auth.presentation.login.LoginScreen
import com.example.composeapp.auth.presentation.register.RegisterScreen
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAppTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar()
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    RegisterScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}