package com.example.a00014669_mad_cw.reusibles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a00014669_mad_cw.R

@Composable
fun FixedNavigation(
    onNavigateMyProducts: () -> Unit,
    onNavigateAllProducts: () -> Unit,
    onNavigateCreate: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = Color.White),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 6.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomIconAndText(
                    modifier = Modifier,
                    icon = R.drawable.baseline_format_align_left_24,
                    text = stringResource(id = R.string.btn_my_products),
                    onClick = onNavigateMyProducts
                )
                CustomIconAndText(
                    modifier = Modifier,
                    icon = R.drawable.baseline_manage_search_24,
                    text = stringResource(id = R.string.btn_all_products),
                    onClick = onNavigateAllProducts
                )
                CustomIconAndText(
                    modifier = Modifier,
                    icon = R.drawable.baseline_create_new_folder_24,
                    text = stringResource(id = R.string.btn_create),
                    onClick = onNavigateCreate
                )
            }
        }
    }
}


@Composable
fun CustomIconAndText(
    modifier: Modifier,
    icon: Int,
    text: String,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.app_name)
        )
        Text(
            text = text,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.black),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}