package com.yandex.ads.sample.kmp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.AdUnits
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material.icons.outlined.GppGood
import androidx.compose.material.icons.outlined.HomeRepairService
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.yandex.mobile.ads.kmp.YandexAds
import com.yandex.ads.sample.kmp.ui.components.SampleTopAppBar

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SampleTopAppBar(title = "Yandex Mobile Ads")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HomeNavTile(
                title = "App Open Ad",
                tag = "app-open-page",
                icon = Icons.AutoMirrored.Outlined.ExitToApp,
                onClick = { navController.navigate("appopen") },
            )
            HomeNavTile(
                title = "Banner Ad",
                tag = "banner-page",
                icon = Icons.Outlined.AdUnits,
                onClick = { navController.navigate("banner") },
            )
            HomeNavTile(
                title = "Interstitial",
                tag = "interstitial-page",
                icon = Icons.Outlined.Fullscreen,
                onClick = { navController.navigate("interstitial") },
            )
            HomeNavTile(
                title = "Rewarded",
                tag = "rewarded-page",
                icon = Icons.Outlined.VideoLibrary,
                onClick = { navController.navigate("rewarded") },
            )
            HomeNavTile(
                title = "Policies",
                tag = "policies-page",
                icon = Icons.Outlined.GppGood,
                onClick = { navController.navigate("policies") },
            )
            HomeNavTile(
                title = "Debug Panel",
                tag = "debug-panel",
                icon = Icons.Outlined.HomeRepairService,
                onClick = { YandexAds.showDebugPanel() },
            )
        }
    }
}

@Composable
private fun HomeNavTile(
    title: String,
    tag: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    ListItem(
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        headlineContent = { Text(title) },
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        modifier = Modifier
            .testTag(tag)
            .fillMaxWidth()
            .clickable(onClick = onClick),
    )
    HorizontalDivider()
}
