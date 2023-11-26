package com.example.a00014669_mad_cw.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a00014669_mad_cw.R

@Composable
fun ParfumeListHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 20.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_format_align_left_24_white),
                contentDescription = stringResource(id = R.string.app_name),
            )
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_flag_circle_24),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }
    }
}