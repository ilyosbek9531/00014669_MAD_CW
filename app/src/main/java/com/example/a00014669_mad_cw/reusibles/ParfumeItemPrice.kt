package com.example.a00014669_mad_cw.reusibles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.a00014669_mad_cw.R
import com.example.a00014669_mad_cw.list.jostFont

@Composable
fun ParfumeItemPrice(price: Double?, fontSize: TextUnit) {
    val textStyle = TextStyle(
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = colorResource(id = R.color.grey_text)
    )

    Text(
        text = "$$price",
        style = textStyle,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}