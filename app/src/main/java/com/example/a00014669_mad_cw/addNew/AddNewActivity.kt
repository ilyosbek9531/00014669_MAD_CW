package com.example.a00014669_mad_cw.addNew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.a00014669_mad_cw.MainActivity
import com.example.a00014669_mad_cw.addNew.ui.theme.ParfumeListTheme
import com.example.a00014669_mad_cw.detailedView.DetailedView
import com.example.a00014669_mad_cw.reusibles.ParfumeListHeader

class AddNewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParfumeListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddNewParfume(
                        onNavigateMyProducts = {
                            this.startActivity(Intent(this, MainActivity::class.java))
                        },
                        onNavigateAllProducts = {
                            this.startActivity(Intent(this, MainActivity::class.java))
                        },
                        onNavigateCreate = {}
                    )
                }
            }
        }
    }
}