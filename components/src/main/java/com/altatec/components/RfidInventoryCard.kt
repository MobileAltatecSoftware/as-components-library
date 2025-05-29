package com.altatec.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * A card component that displays a title and optional subtitle,
 * with support for swipe-to-dismiss gestures using Material 3's SwipeToDismissBox.
 *
 * When swiped from end to start (default), the card animates out with a horizontal slide
 * and fade effect, and then calls [onDismiss].
 *
 * Useful for list items that can be dismissed or deleted with a swipe gesture.
 *
 * @param title The main text displayed prominently in the card.
 * @param subtitle Optional text shown below the title. If empty or blank, it is not rendered.
 * @param backgroundColor The background color of the card. Defaults to [Color.White].
 * @param titleColor The color of the title text. Defaults to [Color.Black].
 * @param subtitleColor The color of the subtitle text. Defaults to [Color.Black].
 * @param enableSwipeStartToEnd Enables swipe gesture from start to end (left to right). Defaults to false.
 * @param enableSwipeEndToStart Enables swipe gesture from end to start (right to left). Defaults to true.
 * @param onDismiss A callback invoked after the swipe animation completes. Typically used to remove the item from the list.
 */
@Composable
fun RfidInventoryCard(
    title: String,
    subtitle: String = "",
    hexTag: String = "",
    backgroundColor: Color = Color.White,
    titleColor: Color = Color.Black,
    subtitleColor: Color = Color.Black,
    enableSwipeStartToEnd: Boolean = false,
    colorSwipeStartToEnd: Color = Color.Green,
    enableSwipeEndToStart: Boolean = true,
    colorSwipeEndToStart: Color = Color.Red,
    onDismiss: ((String) -> Unit)? = null
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = { totalDistance -> totalDistance * 0.5f }
    )
    var visible by remember { mutableStateOf(true) }
    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart && visible) {
            visible = false
            delay(300)
            onDismiss?.invoke(hexTag)
        }
    }

    AnimatedVisibility(
        visible = visible,
        exit = slideOutHorizontally(
            animationSpec = tween(durationMillis = 300),
            targetOffsetX = { fullWidth -> -fullWidth }
        ) + fadeOut(tween(300)),
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                val color by
                animateColorAsState(
                    when (dismissState.targetValue) {
                        SwipeToDismissBoxValue.Settled -> Color.Transparent
                        SwipeToDismissBoxValue.StartToEnd -> colorSwipeStartToEnd
                        SwipeToDismissBoxValue.EndToStart -> colorSwipeEndToStart
                    }
                )
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            },
            enableDismissFromStartToEnd = enableSwipeStartToEnd,
            enableDismissFromEndToStart = enableSwipeEndToStart
        ) {
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        color = titleColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (subtitle.isNotBlank()) {
                        Text(
                            text = subtitle,
                            color = subtitleColor,
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RfidInventoryCardPreview_Default() {
    RfidInventoryCard(
        title = "ABC123",
        subtitle = "Default"
    )
}