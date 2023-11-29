package com.example.a00014669_mad_cw.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a00014669_mad_cw.MainActivity
import com.example.a00014669_mad_cw.addNew.AddNewActivity
import com.example.a00014669_mad_cw.detailedView.DetailedView
import com.example.a00014669_mad_cw.list.ParfumesList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    var showFiltered by remember { mutableStateOf("0") }
    NavHost(navController = navController, startDestination = Screens.ParfumesListScreen.route) {
        composable(Screens.ParfumesListScreen.route) {
            ParfumesList(
                onNavigateMyProducts = {
                    showFiltered = "1"
                },
                onNavigateAllProducts = {
                    showFiltered = "0"
                },
                onNavigateCreate = {
                    showFiltered = "0"
                    context.startActivity(Intent(context, AddNewActivity::class.java))
                },
                onParfumeClick = { parfumeId ->
                    navController.navigate("detailedView/$parfumeId")
                },
                showFiltered = showFiltered
            )
        }

        composable(
            route = "detailedView/{parfumeId}"
        ) { backStackEntry ->
            DetailedView(
                parfumeId = backStackEntry.arguments?.getString("parfumeId")!!,
                onNavigateMyProducts = {
                    showFiltered = "1"
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                onNavigateAllProducts = {
                    showFiltered = "0"
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                onNavigateCreate = {
                    showFiltered = "0"
                    context.startActivity(Intent(context, AddNewActivity::class.java))
                }
            )
        }
    }
}