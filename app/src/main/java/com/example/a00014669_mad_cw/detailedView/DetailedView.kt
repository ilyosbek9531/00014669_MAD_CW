package com.example.a00014669_mad_cw.detailedView

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.R
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponseDoubleListItem
import com.example.a00014669_mad_cw.reusibles.FixedNavigation
import com.example.a00014669_mad_cw.reusibles.ParfumeItemPrice
import com.example.a00014669_mad_cw.reusibles.ParfumeListHeader

@Composable
fun DetailedView(
    parfumeId: String,
    onNavigateAllProducts: () -> Unit,
    viewModel: DetailedViewModel = DetailedViewModel(parfumeId, ParfumeRepository(), onNavigateAllProducts),
    onNavigateMyProducts: () -> Unit,
    onNavigateCreate: () -> Unit
) {

    val parfume by viewModel.parfumeLiveData.observeAsState()
    val fontSize: TextUnit = 18.sp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ParfumeListHeader(hasSearch = false)
        if (parfume != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp, 70.dp, 8.dp, 130.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                Title(title = parfume!!.title)
                ParfumeItemPrice(price = parfume!!.price, fontSize = fontSize)
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        image = parfume!!.image
                    )

                }

                MyDivider()
                Spacer(Modifier.height(5.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    parfume!!.volumes?.let {
                        items(it.size) { index ->
                            val volume = parfume!!.volumes?.get(index)
                            ParfumeVolume(volume = volume)
                        }
                    }
                }

                Spacer(Modifier.height(5.dp))
                MyDivider()

                if (parfume!!.description != null) {
                    Description(description = parfume!!.description!!)
                }

                Spacer(Modifier.height(10.dp))
                ParfumeReusibleComp(item = parfume!!.releaseDate?.take(10), stringResourceId = R.string.release_date)
                ParfumeReusibleComp(item = parfume!!.origin, stringResourceId = R.string.origin)
            }
            EditDeleteButtons(onDeleteButtonClick = {
                viewModel.parfumeLiveData.value?.id?.let { parfumeId ->
                    viewModel.deleteParfumeById(parfumeId)
                }
            })
        }
    }
    FixedNavigation(onNavigateMyProducts, onNavigateAllProducts, onNavigateCreate)
}


@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Left,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
private fun Rating(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(R.drawable.baseline_star_half_24),
            stringResource(id = R.string.cinema_icon_desc),
            contentScale = ContentScale.Crop
        )

        Text(
            text = rating.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Price(price: Double) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_budget_label, price),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun ReleaseDate(releaseDate: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_release_date_label, releaseDate),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = colorResource(id = R.color.grey_text),
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun ActorTextView(actor: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(3.dp, 1.dp),
        text = if (isTheLastOne) actor else "$actor,",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}


@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier.padding(0.dp, 10.dp), color = Color.LightGray

    )
}

@Composable
private fun Image(image: String?) {
    AsyncImage(
        model = image,
        contentDescription = stringResource(id = R.string.parfume_img_text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 12.dp)
            .height(300.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun EditDeleteButtons(onDeleteButtonClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 75.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(modifier = Modifier.weight(1f), onClick = { }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(
                                color = colorResource(id = R.color.edit_color),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Edit, contentDescription = "Edit Icon", tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Edit", color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))

                IconButton(modifier = Modifier.weight(1f), onClick = { onDeleteButtonClick() }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(
                                color = colorResource(id = R.color.delete_color),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete Icon",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Delete", color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ParfumeVolume(volume: ParfumeResponseDoubleListItem?) {
    Box(
        modifier = Modifier
            .background(
                color = Color.Transparent
            )
            .border(width = 0.5.dp, color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        if (volume != null) {
            Text(text = volume.value, color = Color.White, modifier = Modifier.padding(12.dp, 4.dp))
        }
    }
}

@Composable
fun ParfumeReusibleComp(item: String?, @StringRes stringResourceId: Int) {
    val context = LocalContext.current

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(end = 5.dp),
            text = context.getString(stringResourceId),
            color = colorResource(id = R.color.grey_text),
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
        )
        if (!item.isNullOrEmpty()) {
            Text(
                text = item,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600
            )
        }
    }
}

