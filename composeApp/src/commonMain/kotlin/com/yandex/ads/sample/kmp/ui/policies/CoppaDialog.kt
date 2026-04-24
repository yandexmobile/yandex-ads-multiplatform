package com.yandex.ads.sample.kmp.ui.policies

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CoppaDialog(
    onConfirm: (Boolean) -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Outlined.ChildCare, contentDescription = null)
        },
        title = {
            Text("COPPA Compliance")
        },
        text = {
            Text(
                "Is the user age restricted (under 13)? " +
                    "This helps us comply with COPPA (Children's Online Privacy Protection Act)."
            )
        },
        onDismissRequest = { onConfirm(false) },
        dismissButton = {
            TextButton(onClick = { onConfirm(false) }) {
                Text("Not age restricted")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(true) }) {
                Text("Age restricted (under 13)")
            }
        },
    )
}
