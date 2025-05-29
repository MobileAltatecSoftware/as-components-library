package com.altatec.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A reusable composable that displays a titled card with an icon and a list of key-value information.
 *
 * Useful for summarizing grouped information such as battery stats, device status, etc.
 *
 * @param icon Resource ID of the icon to display (e.g., R.drawable.battery).
 * @param title Title text displayed above the divider.
 * @param infoList A list of label-value pairs representing each informational row.
 */
@Composable
fun InfoCard(
    icon: Int,
    title: String,
    infoList: List<Pair<String, String>>
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.divider_grey)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    tint = colorResource(R.color.primary_red)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(thickness = 1.dp)
                infoList.forEach { (label, value) ->
                    BatteryInfoCardItem(label, value)
                }
            }
        }
    }
}

/**
 * Displays a single row of labeled information inside an InfoCard.
 *
 * @param title The label text (left-aligned).
 * @param value The corresponding value (right-aligned).
 */
@Composable
fun BatteryInfoCardItem(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BatteryInfoCardPreview() {
    InfoCard(
        icon = R.drawable.battery,
        title = "Battery",
        infoList = listOf(
            "Voltage" to "3.7V",
            "Temperature" to "28°C",
            "Health" to "Good"
        )
    )
}