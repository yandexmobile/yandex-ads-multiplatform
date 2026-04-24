package com.yandex.ads.sample.kmp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.yandex.ads.sample.kmp.data.Network

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdUnitIdSelector(
    networks: List<Network>,
    onNetworkSelected: (Network) -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "ad-unit-id-selector",
) {
    var selectedNetwork by remember { mutableStateOf(networks.firstOrNull() ?: Network("", "")) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier.testTag(testTag),
    ) {
        TextField(
            value = selectedNetwork.title,
            onValueChange = {},
            readOnly = true,
            label = { Text("Network") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            networks.forEach { network ->
                DropdownMenuItem(
                    text = { Text(network.title) },
                    onClick = {
                        selectedNetwork = network
                        onNetworkSelected(network)
                        expanded = false
                    },
                )
            }
        }
    }
}
