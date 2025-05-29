package com.altatec.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * A custom dialog that allows users to input a file name with validation.
 *
 * It displays a title, icon, input field, and action buttons. Input is limited to alphanumeric
 * characters, underscores, and hyphens (`^[a-zA-Z0-9_-]*$`) and a maximum length of 35 characters.
 *
 * @param title The title shown at the top of the dialog.
 * @param hint The label shown inside the text field as a hint.
 * @param acceptText Text displayed on the accept (positive) button.
 * @param onAccept Callback invoked when the user confirms the input. Receives the trimmed filename.
 * @param dismissText Text displayed on the dismiss (negative) button.
 * @param onDismissRequest Callback invoked when the dialog is dismissed.
 * @param color The primary color used for icons, text highlights, and buttons. Defaults to `primary_red`.
 */
@Composable
fun FileNameDialog(
    title: String,
    hint: String,
    supportText: String,
    acceptText: String,
    onAccept: (String) -> Unit,
    dismissText: String,
    onDismissRequest: () -> Unit,
    color: Color = colorResource(R.color.primary_red)
) {
    var fileName by remember { mutableStateOf("") }
    val allowedRegex = Regex("^[a-zA-Z0-9_\\-\\s]*$")
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.insert_drive_file),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = color
                )
                Text(
                    text = title,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                OutlinedTextField(
                    value = fileName,
                    onValueChange = { newText ->
                        if (newText.length <= 35 && allowedRegex.matches(newText)) {
                            fileName = newText
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    label = { Text(hint) },
                    supportingText = {
                        Row {
                            Text(
                                text = supportText,
                                modifier = Modifier.weight(1f)
                            )
                            Text(text = "${fileName.length}/35")
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Ascii,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = color,
                        unfocusedIndicatorColor = Color(0xFF6D7679),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedSupportingTextColor =
                            if (allowedRegex.matches(fileName)) {
                            Color(0xFF6D7679)
                        } else {
                            color
                        },
                        focusedLabelColor = color
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onDismissRequest() }
                    ) {
                        Text(
                            text = dismissText,
                            color = color
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(
                        onClick = {
                            onAccept(fileName.trim())
                        },
                        enabled = fileName.isNotBlank() && allowedRegex.matches(fileName)
                    ) {
                        Text(
                            text = acceptText,
                            color = if (fileName.isBlank()) {
                                Color.Gray
                            } else {
                                Color.Black
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun FileNameDialogPreview() {
    FileNameDialog(
        title = "Guardar Archivo",
        hint = "Escribe el nombre del archivo",
        supportText = "Solo letras, numeros y guiones",
        acceptText = "Guardar",
        onAccept = {},
        dismissText = "Cancelar",
        onDismissRequest = {}
    )
}