package com.yandex.ads.sample.kmp.ui.policies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun GdprDialog(
    onConfirm: (Boolean) -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Outlined.Handshake, contentDescription = null)
        },
        title = {
            Text("GDPR Consent")
        },
        text = {
            Text(
                "This application contains Yandex advertising code that collects data " +
                    "in order to show you relevant ads that better match your interests. " +
                    "To learn more about how and why Yandex processes your data, " +
                    "see the Privacy Policy."
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
