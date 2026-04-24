package com.yandex.ads.sample.kmp.ui.policies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LocationDialog(
    onConfirm: (Boolean) -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Outlined.Place, contentDescription = null)
        },
        title = {
            Text("Location Consent")
        },
        text = {
            Text(
                "Allow use of your location data for more relevant advertising? " +
                    "Your location data helps us show you ads that are more relevant to where you are."
            )
        },
        onDismissRequest = { onConfirm(false) },
        dismissButton = {
            TextButton(onClick = { onConfirm(false) }) {
                Text("Decline")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(true) }) {
                Text("Accept")
            }
        },
    )
}
