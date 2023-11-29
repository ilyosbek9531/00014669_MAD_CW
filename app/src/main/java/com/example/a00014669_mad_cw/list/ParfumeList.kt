package com.example.a00014669_mad_cw.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.a00014669_mad_cw.R
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import com.example.a00014669_mad_cw.reusibles.FixedNavigation
import com.example.a00014669_mad_cw.reusibles.ParfumeItemPrice
import com.example.a00014669_mad_cw.reusibles.ParfumeListHeader

val jostFont = FontFamily(Font(R.font.jost_regular))

@Composable
fun ParfumesList(
    viewModel: ParfumeListViewModel = ParfumeListViewModel(ParfumeRepository()),
    onNavigateCreate: () -> Unit,
    onNavigateAllProducts: () -> Unit,
    onNavigateMyProducts: () -> Unit,
    onParfumeClick: (String) -> Unit = {},
    showFiltered: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ParfumeListHeader()

        val resParfumes by viewModel.parfumesLiveData.observeAsState()

        val resReversedParfumes = resParfumes?.reversed()

        val parfumes = if (showFiltered == "1") {
            resReversedParfumes?.filter { it.isItTrue == "1" } ?: emptyList()
        } else {
            resReversedParfumes ?: emptyList()
        }

        if (!parfumes.isNullOrEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 60.dp, 0.dp, 90.dp)
            ) {
                items(items = parfumes.toList()) { item ->
                    ParfumeItem(parfume = item, onParfumeClick)
                }
            }
        }

        FixedNavigation(onNavigateMyProducts, onNavigateAllProducts, onNavigateCreate)
    }
}

@Composable
private fun ParfumeItem(parfume: Parfume, onParfumeClick: (String) -> Unit) {
    val minVal = parfume.volumes?.map { it.value.toInt() }
    val lowestValue = minVal?.minOrNull()
    val fontSize: TextUnit = 12.sp
    ElevatedCard(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent, contentColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onParfumeClick(parfume.id)
            }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ParfumeItemTitle(title = parfume.title)
                }
                ParfumeItemMinVolume(volume = lowestValue)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.weight(0.8f)) {
                    ParfumeItemPrice(price = parfume.price, fontSize = fontSize)
                }
                Box(modifier = Modifier.weight(1.2f)) {
                    ParfumeItemImg(image = parfume.image)
                }
            }
        }
    }
}

@Composable
private fun ParfumeItemTitle(title: String) {
    Text(
        text = title,
        fontSize = 17.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun ParfumeItemMinVolume(volume: Int?) {
    Text(
        text = "$volume ml",
        fontSize = 12.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.grey_text),
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun ParfumeItemImg(image: String?) {
    AsyncImage(
        model = image,
        contentDescription = stringResource(id = R.string.parfume_img_text),
        modifier = Modifier
            .widthIn(min = 0.dp, max = Dp.Infinity)
            .height(150.dp)
    )
}