package com.altatec.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A customizable card component used to display a title, optional subtitle, an optional icon,
 * and a progress indicator. Useful for status displays, selection states, or proximity indicators.
 *
 * @param title The main text displayed prominently at the top of the card.
 * @param subtitle Optional secondary text displayed below the title. If empty, it is not shown.
 * @param isClickable Whether the card should be clickable. If `true`, [onClick] will be invoked on tap.
 * @param onClick Lambda to execute when the card is clicked. Ignored if [isClickable] is `false`.
 * @param isFound Whether the item represented by this card has been found. If `true`, a red border and a check icon are shown.
 * @param showProgress Whether to display a horizontal progress indicator.
 * @param progressValue An integer from 0 to 100 representing the percentage progress shown when [showProgress] is `true`.
 */
@Composable
fun RfidSearchCard(
    title: String,
    subtitle: String = "",
    isClickable: Boolean = false,
    onClick: () -> Unit = {},
    isFound: Boolean = false,
    showProgress: Boolean = false,
    progressValue: Int = 0,
) {
    val progressState by animateFloatAsState(targetValue = progressValue / 100f)
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(isClickable) { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = if (isFound) {
            BorderStroke(2.dp, colorResource(R.color.primary_red))
        } else {
            BorderStroke(0.dp, Color.Transparent)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.check_circle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = if (isFound) {
                        ColorFilter.tint(colorResource(R.color.primary_red))
                    } else {
                        ColorFilter.tint(Color.LightGray)
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (subtitle.isNotBlank()) {
                        Text(
                            text = subtitle,
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            AnimatedVisibility(showProgress) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progressState },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = colorResource(R.color.primary_red),
                        trackColor = Color.LightGray,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RfidSearchCardNotFoundWithoutProgressPreview() {
    RfidSearchCard(
        title = "1234567890",
        subtitle = "Articulo"
    )
}

@Preview(showBackground = true)
@Composable
fun RfidSearchCardNFoundWithoutProgressPreview() {
    RfidSearchCard(
        title = "1234567890",
        subtitle = "WAUGFCF47PA059539",
        isFound = true
    )
}

@Preview(showBackground = true)
@Composable
fun RfidSearchCardNotFoundWithProgressPreview() {
    RfidSearchCard(
        title = "1234567890",
        subtitle = "WAUGFCF47PA059539",
        showProgress = true,
        progressValue = 0
    )
}

@Preview(showBackground = true)
@Composable
fun RfidSearchCardFoundWithProgressPreview() {
    RfidSearchCard(
        title = "1234567890",
        subtitle = "WAUGFCF47PA059539",
        isFound = true,
        showProgress = true,
        progressValue = 50
    )
}