package com.altatec.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A composable that displays a battery percentage along with a progress bar.
 *
 * @param percentage Battery level (expected range: 0 to 100).
 * @param color Main color used for text and progress bar.
 * @param trackColor Background color of the progress bar.
 * @param textSize Font size of the percentage number.
 * @param unitSize Font size of the "%" symbol.
 * @param indicatorHeight Height of the linear progress bar.
 */
@Composable
fun BatteryPercentage(
    percentage: Int,
    color: Color = colorResource(R.color.primary_red),
    trackColor: Color = Color.LightGray,
    textSize: Int = 40,
    unitSize: Int = 24,
    indicatorHeight: Dp = 8.dp
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = percentage.toString(),
            color = color,
            fontSize = textSize.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "%",
            modifier = Modifier.padding(bottom = 4.dp),
            color = color,
            fontSize = unitSize.sp,
            fontWeight = FontWeight.Bold
        )
    }
    LinearProgressIndicator(
        progress = { percentage.coerceIn(0, 100) / 100f },
        modifier = Modifier
            .fillMaxWidth()
            .height(indicatorHeight),
        color = color,
        trackColor = trackColor,
    )

}

@Composable
@Preview(showBackground = true)
fun BatteryPercentagePreview() {
    BatteryPercentage(percentage = 50)
}