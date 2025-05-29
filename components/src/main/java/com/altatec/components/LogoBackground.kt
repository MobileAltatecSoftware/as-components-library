package com.altatec.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LogoBackground() {
    Image(
        painter = painterResource(id = R.drawable.background_logo),
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .then(Modifier.scale(1.25f)),
        alignment = Alignment.CenterStart,
        contentScale = ContentScale.None
    )
}

@Composable
@Preview(showBackground = true)
fun BackgroundPreview() {
    LogoBackground()
}