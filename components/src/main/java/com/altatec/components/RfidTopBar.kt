package com.altatec.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

/**
 * A customizable top app bar with a centered title, a back navigation icon, and optional action content.
 *
 * This component is commonly used as the header of a screen, providing a consistent layout for navigation
 * and optional actions.
 *
 * @param title The text displayed in the center of the top app bar.
 * @param titleColor The color of the title text. Defaults to [Color.Black].
 * @param onBackClick Callback triggered when the back navigation icon is clicked.
 * @param backIcon The icon used for the back navigation button. Defaults to [Icons.AutoMirrored.Filled.ArrowBack].
 * @param backIconDescription The content description of the back icon, used for accessibility. Defaults to `"Back"`.
 * @param actionContent Optional composable lambda to display custom action buttons or icons on the right side of the top bar.
 * @param backgroundColor The background color of the top app bar. Defaults to [Color.White].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RfidTopBar(
    title: String,
    titleColor: Color = Color.Black,
    onBackClick: (() -> Unit),
    backIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    backIconDescription: String = "Back",
    actionContent: (@Composable () -> Unit)? = null,
    backgroundColor: Color = Color.White,
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = titleColor,
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
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = backIcon,
                    contentDescription = backIconDescription,
                    tint = titleColor
                )
            }
        },
        actions = {
            actionContent?.invoke()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RfidTopBarPreview() {
    RfidTopBar(
        title = "RFID TopBar",
        onBackClick = {},
        actionContent = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    )
}