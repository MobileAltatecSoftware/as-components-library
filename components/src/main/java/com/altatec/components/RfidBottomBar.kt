package com.altatec.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A bottom bar component used in RFID workflows.
 *
 * Displays one or two buttons depending on whether dual mode is enabled.
 *
 * @param isDualMode If `true`, two buttons are shown. If `false`, only the primary button is visible.
 * @param primaryButtonText The text label for the primary button.
 * @param onPrimaryClick The action to perform when the primary button is clicked.
 * @param isPrimaryEnabled Enables or disables the primary button.
 * @param secondaryButtonText The text label for the secondary button (visible only if `isDualMode` is `true`).
 * @param onSecondaryClick The action to perform when the secondary button is clicked.
 * @param isSecondaryEnabled Enables or disables the secondary button.
 */
@Composable
fun RfidBottomBar(
    isDualMode: Boolean,
    primaryButtonText: String,
    onPrimaryClick: (() -> Unit),
    isPrimaryEnabled: Boolean = true,
    secondaryButtonText: String = "",
    onSecondaryClick: (() -> Unit) = { },
    isSecondaryEnabled: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = colorResource(R.color.divider_grey)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isDualMode) {
                Button(
                    onClick = { onSecondaryClick() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                    enabled = isSecondaryEnabled,
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.secondary_red))
                ) {
                    Text(
                        text = secondaryButtonText,
                        color = colorResource(R.color.primary_red)
                    )
                }
            }
            Button(
                onClick = { onPrimaryClick() },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                enabled = isPrimaryEnabled,
                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_red))
            ) {
                Text(
                    text = primaryButtonText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RfidBottomBarPreview() {
    RfidBottomBar(
        isDualMode = false,
        primaryButtonText = "Start",
        onPrimaryClick = {},
        secondaryButtonText = "",
        onSecondaryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun InventoryBottomBarDualModePreview() {
    RfidBottomBar(
        isDualMode = true,
        primaryButtonText = "Continue",
        onPrimaryClick = {},
        secondaryButtonText = "Resume",
        onSecondaryClick = {}
    )
}