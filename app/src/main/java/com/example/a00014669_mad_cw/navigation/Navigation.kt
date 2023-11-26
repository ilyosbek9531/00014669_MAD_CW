package com.example.a00014669_mad_cw.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a00014669_mad_cw.addNew.AddNewActivity
import com.example.a00014669_mad_cw.detailedView.DetailedView
import com.example.a00014669_mad_cw.list.ParfumesList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.ParfumesListScreen.route) {
        composable(Screens.ParfumesListScreen.route) {
            ParfumesList(onNavigateMyProducts = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            },onAddNewMovieClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            }, onMovieClick = { parfumeId ->
                    navController.navigate("detailedView/$parfumeId")
                }
            )
        }

        composable(
            route = "detailedView/{parfumeId}"
        ) { backStackEntry ->
            DetailedView(parfumeId = backStackEntry.arguments?.getString("parfumeId")!!)
        }
    }
}