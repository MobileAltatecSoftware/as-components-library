package com.altatec.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
 * A customizable card component that displays an icon, a title, and an optional description.
 *
 * This component is ideal for showcasing information blocks, dashboard entries,
 * feature highlights, or shortcuts. It supports toggling between primary and secondary
 * color styles for flexible design needs.
 *
 * @param titleText The main title text displayed prominently on the card.
 * @param descriptionText Optional secondary text displayed below the title.
 * @param iconResId Drawable resource ID of the icon displayed on the left.
 * @param onCardClick Callback triggered when the card is tapped.
 * @param usePrimaryStyle When true, the card uses [primaryBackgroundColor] as its background
 *                        and [secondaryContentColor] for text and icon. If false, the colors are inverted.
 * @param primaryBackgroundColor The background color when [usePrimaryStyle] is true.
 *                               Default is [R.color.primary_red].
 * @param secondaryContentColor The color used for text and icon when [usePrimaryStyle] is true.
 *                              Default is [R.color.divider_grey].
 */
@Composable
fun InfoCardItem(
    titleText: String,
    descriptionText: String? = "",
    @DrawableRes iconResId: Int,
    onCardClick: () -> Unit = {},
    usePrimaryStyle: Boolean = true,
    primaryBackgroundColor: Color = colorResource(R.color.primary_red),
    secondaryContentColor: Color = colorResource(R.color.divider_grey),
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (usePrimaryStyle) primaryBackgroundColor else secondaryContentColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = if (usePrimaryStyle) secondaryContentColor else primaryBackgroundColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = titleText,
                    color = if (usePrimaryStyle) secondaryContentColor else primaryBackgroundColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                if (!descriptionText.isNullOrBlank()) {
                    Text(
                        text = descriptionText,
                        color = if (usePrimaryStyle) secondaryContentColor else primaryBackgroundColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCardRedPreview() {
    InfoCardItem(
        titleText = "Inventario",
        descriptionText = "Escanea y registra chasises con RFID",
        iconResId = R.drawable.list
    )
}

@Preview(showBackground = true)
@Composable
fun HomeCardGreyPreview() {
    InfoCardItem(
        titleText = "Inventario",
        iconResId = R.drawable.list,
        usePrimaryStyle = false
    )
}