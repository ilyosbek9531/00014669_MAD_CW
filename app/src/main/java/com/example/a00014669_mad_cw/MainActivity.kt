package com.example.a00014669_mad_cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a00014669_mad_cw.navigation.Navigation
import com.example.a00014669_mad_cw.navigation.Screens
import com.example.a00014669_mad_cw.ui.theme._00014669_MAD_CWTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _00014669_MAD_CWTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController, context = this)

                    navController.navigate(Screens.ParfumesListScreen.route)
                }
            }
        }
    }
}