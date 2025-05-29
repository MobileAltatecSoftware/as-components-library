package com.altatec.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A reusable modal bottom sheet that displays a list of selectable items.
 *
 * Commonly used for quick navigation or user actions, like accessing settings
 * or additional information.
 *
 * @param sheetState Controls the visibility and animation of the bottom sheet.
 * @param scope The [CoroutineScope] used to trigger hide animations before executing actions.
 * @param items A list of [BottomSheetItem] representing each actionable row in the sheet.
 * @param onDismiss Callback triggered when the sheet is dismissed.
 * @param versionText Optional footer text, typically used to show the app version.
 * @param modelText Optional footer text, typically used to show the device model.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    items: List<BottomSheetItem>,
    onDismiss: () -> Unit,
    versionText: String = "",
    modelText: String = ""
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(horizontal = 8.dp)
            ) {
                items.forEach { item ->
                    ButtonSheetItem(
                        title = item.title,
                        iconResId = item.iconResId,
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismiss()
                                    item.onClick()
                                }
                            }
                        }
                    )
                }

                if (versionText.isNotBlank()) {
                    Row(
                        modifier = Modifier
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Version $versionText",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (modelText.isNotBlank()) {
                    Row(
                        modifier = Modifier
                            .height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = modelText,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }
}

/**
 * Represents a selectable action within the [AppBottomSheet].
 *
 * @param title The text label displayed for the item.
 * @param iconResId Optional resource ID of the icon to show on the left.
 * @param contentDescription Accessibility description for the icon.
 * @param onClick Action to be executed when the item is selected.
 */
data class BottomSheetItem(
    val title: String,
    @DrawableRes val iconResId: Int? = null,
    val contentDescription: String? = null,
    val onClick: () -> Unit
)

/**
 * A single row inside the bottom sheet, showing an optional icon and a label.
 *
 * @param title The text label to display.
 * @param iconResId Optional drawable resource ID for the leading icon.
 * @param contentDescription Optional accessibility description for the icon.
 * @param onClick Callback triggered when the row is clicked.
 */
@Composable
fun ButtonSheetItem(
    title: String,
    iconResId: Int? = null,
    contentDescription: String? = title,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconResId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = contentDescription
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        listOf(
            BottomSheetItem("Battery Info", R.drawable.altatec_logo) {},
            BottomSheetItem("Settings") {}
        ).forEach { item ->
            ButtonSheetItem(
                title = item.title,
                iconResId = item.iconResId,
            )
        }

        Row(
            modifier = Modifier.height(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Version 1.0",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}