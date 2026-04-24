package com.yandex.ads.sample.kmp.ui.policies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yandex.mobile.ads.kmp.YandexAds
import com.yandex.ads.sample.kmp.ui.components.SampleTopAppBar

@Composable
fun PoliciesScreen(navController: NavHostController) {
    var showGdprDialog by remember { mutableStateOf(false) }
    var showLocationDialog by remember { mutableStateOf(false) }
    var showCoppaDialog by remember { mutableStateOf(false) }
    var userConsent by remember { mutableStateOf(false) }
    var locationTracking by remember { mutableStateOf(false) }
    var ageRestricted by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SampleTopAppBar(title = "Policies", onBack = { navController.popBackStack() })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            // GDPR Policy
            ListItem(
                headlineContent = {
                    Text(
                        if (userConsent) "User consent enabled"
                        else "User consent disabled"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingContent = {
                    Button(
                        onClick = { showGdprDialog = true },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Open")
                    }
                },
            )

            // Location Policy
            ListItem(
                headlineContent = {
                    Text(
                        if (locationTracking) "Location consent enabled"
                        else "Location consent disabled"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingContent = {
                    Button(
                        onClick = { showLocationDialog = true },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Open")
                    }
                },
            )

            // COPPA Policy
            ListItem(
                headlineContent = {
                    Text(
                        if (ageRestricted) "User is age restricted"
                        else "User is not age restricted"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingContent = {
                    Button(
                        onClick = { showCoppaDialog = true },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Open")
                    }
                },
            )
        }

        // Dialogs
        if (showGdprDialog) {
            GdprDialog(
                onConfirm = { consent ->
                    YandexAds.setUserConsent(consent)
                    userConsent = consent
                    showGdprDialog = false
                }
            )
        }

        if (showLocationDialog) {
            LocationDialog(
                onConfirm = { consent ->
                    YandexAds.setLocationTracking(consent)
                    locationTracking = consent
                    showLocationDialog = false
                }
            )
        }

        if (showCoppaDialog) {
            CoppaDialog(
                onConfirm = { restricted ->
                    YandexAds.setAgeRestricted(restricted)
                    ageRestricted = restricted
                    showCoppaDialog = false
                }
            )
        }
    }
}
