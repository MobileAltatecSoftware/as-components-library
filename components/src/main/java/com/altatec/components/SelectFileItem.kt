package com.altatec.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A composable item representing a selectable row with an icon, label, and radio button.
 *
 * Commonly used in scenarios where the user must choose a single option from a list,
 * such as file selection or preference settings.
 *
 * @param label The main text displayed next to the icon.
 * @param iconResId Resource ID of the icon shown at the start of the item.
 * @param iconTint The color applied to the icon. Defaults to [Color.Unspecified], meaning the default icon color will be used.
 * @param selected Whether this item is currently selected. Defaults to `false`.
 * @param onClick Callback triggered when the item or its radio button is clicked.
 * @param radioButtonColor The color applied to both the selected and unselected states of the radio button.
 *                         Defaults to [Color.Unspecified], which uses the default styling.
 */
@Composable
fun SelectFileItem(
    label: String,
    @DrawableRes iconResId: Int = R.drawable.altatec_logo,
    iconTint: Color = colorResource(R.color.primary_red),
    selected: Boolean = false,
    onClick: () -> Unit = {},
    radioButtonColor: Color = colorResource(R.color.primary_red)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .height(72.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = iconTint
            )
            Text(
                text = label,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                fontSize = 16.sp
            )
            RadioButton(
                selected = selected,
                onClick = { onClick() },
                colors = RadioButtonDefaults.colors(
                    selectedColor = radioButtonColor,
                    unselectedColor = radioButtonColor
                )
            )
        }
        HorizontalDivider(thickness = 1.dp, color = colorResource(R.color.divider_grey))
    }
}

@Preview(showBackground = true)
@Composable
fun SearchFileItemSelectedPreview() {
    SelectFileItem(
        label = "Taller123 2024-12-18 14:02:00.xls",
        iconResId = R.drawable.altatec_logo,
        iconTint = colorResource(R.color.primary_red),
        selected = true,
        radioButtonColor = colorResource(R.color.primary_red)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchFileItemPreview() {
    SelectFileItem(
        label = "Taller123 2024-12-18 14:02:00.xls",
        iconResId = R.drawable.altatec_logo,
        iconTint = colorResource(R.color.primary_red),
        selected = false,
        radioButtonColor = colorResource(R.color.primary_red)
    )
}