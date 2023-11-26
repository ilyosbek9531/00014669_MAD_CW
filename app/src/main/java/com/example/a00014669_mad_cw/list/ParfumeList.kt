package com.example.a00014669_mad_cw.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a00014669_mad_cw.R
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import com.example.a00014669_mad_cw.navigation.FixedNavigation

val jostFont = FontFamily(Font(R.font.jost_regular))

@Composable
fun ParfumesList(
    viewModel: ParfumeListViewModel = ParfumeListViewModel(ParfumeRepository()),
    onAddNewMovieClick: () -> Unit,
    onNavigateMyProducts: () -> Unit,
    onMovieClick: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ParfumeListHeader()

        val movies by viewModel.moviesLiveData.observeAsState()

        if (!movies.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp, 60.dp, 0.dp, 90.dp)) {
                items(items = movies!!.toList(), itemContent = { item ->
                    MovieItem(movie = item, onMovieClick)
                })
            }
        }

        FixedNavigation(onNavigateMyProducts, onAddNewMovieClick)
    }
}

@Composable
private fun MovieItem(movie: Parfume, onMovieClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bleak_yellow),
            contentColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onMovieClick(movie.id)
                }
        ) {
            MovieItemName(name = movie.name)
            if (!movie.description.isNullOrEmpty())
                MovieItemDesc(desc = movie.description)
        }
    }
}

@Composable
private fun MovieItemName(name: String) {
    Text(
        text = name,
        fontSize = 21.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun MovieItemDesc(desc: String) {
    Text(
        text = desc,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        fontSize = 18.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left
    )
}