package com.altatec.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A customizable top app bar with a centered title, a navigation icon on the left,
 * and an action icon on the right.
 *
 * This component is useful for consistent UI headers across different screens, allowing
 * developers to define icons, colors, and behavior for user interactions.
 *
 * @param title The text to be displayed at the center of the top app bar.
 * @param navIconResId Resource ID for the navigation icon displayed on the left. Default is [R.drawable.altatec_logo].
 * @param onNavIconClick Lambda to be invoked when the navigation icon is clicked.
 * @param navIconTint Tint color applied to the navigation icon.
 * @param actionIconResId Resource ID for the action icon displayed on the right. Default is [R.drawable.menu].
 * @param onActionClick Lambda to be invoked when the action icon is clicked.
 * @param actionIconTint Tint color applied to the action icon.
 * @param titleColor Color of the title text.
 * @param containerColor Background color of the top app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    @DrawableRes navIconResId: Int = R.drawable.altatec_logo,
    onNavIconClick: () -> Unit = {},
    navIconTint: Color = colorResource(R.color.primary_red),
    @DrawableRes actionIconResId: Int = R.drawable.menu,
    onActionClick: () -> Unit = {},
    actionIconTint: Color = colorResource(R.color.primary_red),
    titleColor: Color = Color.Black,
    containerColor: Color = Color.White
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = titleColor
        ),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavIconClick) {
                Image(
                    painter = painterResource(id = navIconResId),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(navIconTint)
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    painter = painterResource(id = actionIconResId),
                    contentDescription = null,
                    tint = actionIconTint
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTopBarPreview() {
    CustomTopBar(
        title = "Altatec RFID"
    )
}