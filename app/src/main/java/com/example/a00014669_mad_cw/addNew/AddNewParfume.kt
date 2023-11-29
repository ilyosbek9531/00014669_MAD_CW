package com.example.a00014669_mad_cw.addNew

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a00014669_mad_cw.MainActivity
import com.example.a00014669_mad_cw.R
import com.example.a00014669_mad_cw.data.ParfumeRepository
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponseDoubleListItem
import com.example.a00014669_mad_cw.reusibles.FixedNavigation
import com.example.a00014669_mad_cw.reusibles.ParfumeListHeader
import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat


@Composable
fun AddNewParfume(
    viewModel: AddNewParfumeViewModel = AddNewParfumeViewModel(ParfumeRepository()),
    onNavigateMyProducts: () -> Unit,
    onNavigateAllProducts: () -> Unit,
    onNavigateCreate: () -> Unit
) {
    val localContext = LocalContext.current

    val title = remember { mutableStateOf("") }
    val image = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val releaseDate = remember { mutableStateOf("") }
    val originOptions = listOf(
        "Uzbekistan", "Russia", "China", "Germany", "USA", "France", "Qatar", "England", "Singapure"
    )
    val isOriginExpanded = remember {
        mutableStateOf(false)
    }
    val volumeOptions = listOf(50, 100, 150, 200, 300, 400, 500).toMutableList()
    val selectedValues = remember { mutableListOf<Int>() }
    val selectedOriginText = remember { mutableStateOf(originOptions[0]) }

    val response by viewModel.insertResponseLiveData.observeAsState()
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.bg)
            )
    ) {
        ParfumeListHeader(hasSearch = false)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 60.dp, 8.dp, 140.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CreateNewParfumePageTitle()
            Spacer(modifier = Modifier.height(15.dp))
            TitleInput(title = title.value, onTitleChange = { title.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            ImageInput(image = image.value, onImageChange = { image.value = it })
            Spacer(Modifier.height(16.dp))
            DescriptionInput(
                description = description.value,
                onDescriptionChange = { description.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            VolumeOptions(volumeOptions, selectedValues)
            Spacer(modifier = Modifier.height(15.dp))
            Price(price = price.value, onPriceChanged = { price.value = it })
            ReleaseDate(
                releaseDate = releaseDate.value,
                onReleaseDateChanged = { releaseDate.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(15.dp))
            Origin(
                isExpanded = isOriginExpanded.value,
                onExpandedChanged = { isOriginExpanded.value = !isOriginExpanded.value },
                selectedOptionText = selectedOriginText.value,
                onSelectedOptionChanged = { selectedOriginText.value = it },
                options = originOptions
            )

            // actors text input - comma separated 4x

            Spacer(Modifier.height(16.dp))

        }

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 19.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                localContext.startActivity(Intent(localContext, MainActivity::class.java))
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp),

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
                    AddNewButton {
                        val constructedParfume: Parfume? = constructParfumeIfInputValid(
                            titleInput = title.value,
                            imageInput = image.value,
                            descriptionInput = description.value,
                            priceInput = price.value,
                            releaseDateInput = releaseDate.value,
                            originInput = selectedOriginText.value,
                            context = localContext,
                            selectedValues = selectedValues.toList()
                        )

                        if (constructedParfume != null) {
                            viewModel.saveNewParfume(
                                constructedParfume
                            )
                        }
                    }
                }
            }
        }
    }
    FixedNavigation(onNavigateMyProducts, onNavigateAllProducts, onNavigateCreate)
}

@Composable
private fun CreateNewParfumePageTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.title_activity_add_new_parfume),
        color = Color.White,
        fontSize = 26.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleInput(title: String, onTitleChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = Color.White
        ),
        value = title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onTitleChange(it) },
        label = {
            Text(stringResource(id = R.string.parfume_title_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageInput(image: String, onImageChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = Color.White
        ),
        value = image,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onImageChange(it) },
        label = {
            Text(stringResource(id = R.string.parfume_image_input_hint))
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = Color.White
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) },
        label = {
            Text(stringResource(id = R.string.parfume_desc_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Price(price: String, onPriceChanged: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = Color.White
        ),
        value = price,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onPriceChanged(it) },
        label = {
            Text(stringResource(id = R.string.parfume_price_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReleaseDate(releaseDate: String, onReleaseDateChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Label(label = "Enter product release date in correct format")
        TextField(modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black, containerColor = Color.White
            ),
            value = releaseDate,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onReleaseDateChanged(it) },
            label = {
                Text(stringResource(id = R.string.parfume_release_date_input_hint))
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VolumeOptions(
    volumeOptions: List<Int>, selectedValues: MutableList<Int>
) {
    Label(label = stringResource(id = R.string.select_volumes))
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        volumeOptions.forEachIndexed { index, volume ->
            val isSelected = remember { mutableStateOf(selectedValues.contains(volume)) }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(45.dp)
                    .background(
                        color = if (isSelected.value) colorResource(id = R.color.edit_color) else Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        isSelected.value = !isSelected.value
                        if (isSelected.value) {
                            selectedValues.add(volume)
                        } else {
                            selectedValues.remove(volume)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = volume.toString(),
                    fontSize = 16.sp,
                    color = if (isSelected.value) Color.White else Color.Black,
                    modifier = Modifier
                )
            }
            if (index < volumeOptions.size - 1) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Origin(
    isExpanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    selectedOptionText: String,
    onSelectedOptionChanged: (String) -> Unit,
    options: List<String>
) {
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {
        onExpandedChanged(it)
    }) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(stringResource(id = R.string.parfume_origin_menu_hint)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = Color.Black, containerColor = Color.White
            )
        )

        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChanged(false)
            }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    onSelectedOptionChanged(selectionOption)
                    onExpandedChanged(false)
                }, text = { Text(text = selectionOption) })
            }
        }
    }
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        }, modifier = Modifier
            .fillMaxWidth()
            .height(45.dp), colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.edit_color), contentColor = Color.White
        )

    ) {
        Text(
            fontSize = 17.sp, text = stringResource(id = R.string.save_btn_text)
        )
    }
}

private fun constructParfumeIfInputValid(
    titleInput: String?,
    imageInput: String?,
    descriptionInput: String?,
    priceInput: String?,
    releaseDateInput: String?,
    originInput: String?,
    context: Context,
    selectedValues: List<Int>?
): Parfume? {
    if (titleInput.isNullOrEmpty() || imageInput.isNullOrEmpty() || descriptionInput.isNullOrEmpty() || priceInput.isNullOrEmpty() || releaseDateInput.isNullOrEmpty() || originInput.isNullOrEmpty()) {
        Toast.makeText(
            context,
            context.resources.getString(R.string.parfume_all_fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    val dateFormat = SimpleDateFormat("YYYY-MM-DD")

    try {
        dateFormat.parse(releaseDateInput)
    } catch (e: ParseException) {
        Toast.makeText(
            context,
            context.resources.getString(R.string.parfume_date_format_incorrect_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    return Parfume(
        title = titleInput,
        description = descriptionInput,
        price = priceInput.toDouble(),
        image = imageInput,
        origin = originInput,
        releaseDate = "$releaseDateInput 00:00:00",
        volumes = selectedValues as List<ParfumeResponseDoubleListItem>?,
        isItTrue = "1"
    )
}


@Composable
private fun Label(label: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = label,
        color = colorResource(id = R.color.grey_text),
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif
    )
}



