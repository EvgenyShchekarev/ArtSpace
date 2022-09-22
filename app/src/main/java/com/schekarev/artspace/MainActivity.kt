package com.schekarev.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schekarev.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpace()
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var currentState by remember { mutableStateOf(1) }
    when (currentState) {
        1 -> {
            ArtSpaceComposition(
                drawableResourceId = R.drawable.utro_v_sosnovom_lesu,
                contentDescriptionResourceId = R.string.morning_in_a_pine_forest,
                titleResourceId = R.string.morning_in_a_pine_forest,
                authorResourceId = R.string.shishkin,
                onButtonNextClick = { currentState = 2 },
                onButtonPreviousClick = { currentState = 5 }
            )
        }
        2 -> {
            ArtSpaceComposition(
                drawableResourceId = R.drawable.devyatiy_val,
                contentDescriptionResourceId = R.string.ninth_wave,
                titleResourceId = R.string.ninth_wave,
                authorResourceId = R.string.aivazovsky,
                onButtonNextClick = { currentState = 3 },
                onButtonPreviousClick = { currentState = 1 }
            )
        }
        3 -> {
            ArtSpaceComposition(
                drawableResourceId = R.drawable.devochka_persik,
                contentDescriptionResourceId = R.string.girl_with_peaches,
                titleResourceId = R.string.girl_with_peaches,
                authorResourceId = R.string.serov_valentin,
                onButtonNextClick = { currentState = 4 },
                onButtonPreviousClick = { currentState = 2 }
            )
        }
        4 -> {
            ArtSpaceComposition(
                drawableResourceId = R.drawable.pompei,
                contentDescriptionResourceId = R.string.the_last_day_of_pompeii,
                titleResourceId = R.string.the_last_day_of_pompeii,
                authorResourceId = R.string.brulov_karl,
                onButtonNextClick = { currentState = 5 },
                onButtonPreviousClick = { currentState = 3 }
            )
        }
        5 -> {
            ArtSpaceComposition(
                drawableResourceId = R.drawable.mona_liza,
                contentDescriptionResourceId = R.string.mona_liza,
                titleResourceId = R.string.mona_liza,
                authorResourceId = R.string.leonardo_da_vinci,
                onButtonNextClick = { currentState = 1 },
                onButtonPreviousClick = { currentState = 4 }
            )
        }
    }
}

@Composable
fun ArtSpaceComposition(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    titleResourceId: Int,
    authorResourceId: Int,
    onButtonNextClick: () -> Unit,
    onButtonPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(25.dp)
    ) {
        Picture(
            drawableResourceId = drawableResourceId,
            contentDescriptionResourceId = contentDescriptionResourceId,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TitleAndAuthorText(
            titleResourceId = titleResourceId,
            authorResourceId = authorResourceId,
            modifier = Modifier
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonsNavigation(
            onButtonNextClick = onButtonNextClick,
            onButtonPreviousClick = onButtonPreviousClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Picture(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        elevation = 20.dp,
        modifier = modifier
            .wrapContentHeight()
            .border(
                BorderStroke(2.dp, Color(105, 105, 105))
            )
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = modifier
                .padding(20.dp)
        )
    }
}

@Composable
fun TitleAndAuthorText(
    modifier: Modifier = Modifier,
    titleResourceId: Int,
    authorResourceId: Int
) {
    Surface(
        color = MaterialTheme.colors.background,
        elevation = 20.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(titleResourceId),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(authorResourceId),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ButtonsNavigation(
    modifier: Modifier = Modifier,
    onButtonNextClick: () -> Unit,
    onButtonPreviousClick: () -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = onButtonPreviousClick
        ) {
            Text(
                text = stringResource(id = R.string.button_previous),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = onButtonNextClick
        ) {
            Text(
                text = stringResource(id = R.string.button_next),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}