package com.github.users

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.github.users.navigation.NavigationComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold {
                NavigationComponent(this, navController)
            }
        }
    }
}