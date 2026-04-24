package com.yandex.ads.sample.kmp.ui.banner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.yandex.mobile.ads.kmp.banner.Banner
import com.yandex.mobile.ads.kmp.banner.BannerAdSize
import com.yandex.mobile.ads.kmp.banner.BannerEvents
import com.yandex.mobile.ads.kmp.common.AdRequest
import com.yandex.ads.sample.kmp.data.NetworkProvider
import com.yandex.ads.sample.kmp.ui.components.AdUnitIdSelector
import com.yandex.ads.sample.kmp.ui.components.LogSection
import com.yandex.ads.sample.kmp.ui.components.SampleTopAppBar
import com.yandex.mobile.ads.kmp.banner.rememberBannerAdState

private val DefaultBannerWidth = 320.dp
private val DefaultBannerHeight = 50.dp

@Composable
fun BannerAdScreen(navController: NavHostController) {
    val networks = NetworkProvider.bannerNetworks
    var adUnitId by remember { mutableStateOf(networks.first().adUnitId) }
    var logs by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var adRequest by remember { mutableStateOf(AdRequest(adUnitId = adUnitId)) }

    fun appendLog(msg: String) {
        logs = if (logs.isEmpty()) msg else "$logs\n$msg"
    }

    val bannerState = rememberBannerAdState(
        adSize = BannerAdSize.Inline(DefaultBannerWidth, DefaultBannerHeight),
        events = BannerEvents(
            onAdLoaded = {
                isLoading = false
                appendLog("Ad loaded")
            },
            onAdFailedToLoad = { error ->
                isLoading = false
                appendLog("Ad failed: ${error.description}")
            },
            onAdClicked = {
                appendLog("Ad clicked")
            },
            onImpression = { data ->
                appendLog("Ad impression")
            },
        )
    )

    Scaffold(
        topBar = {
            SampleTopAppBar(title = "Banner Ad", onBack = { navController.popBackStack() })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            AdUnitIdSelector(
                networks = networks,
                onNetworkSelected = { adUnitId = it.adUnitId },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = {
                    isLoading = true
                    adRequest = adRequest.copy(adUnitId = adUnitId)
                    appendLog("Loading banner...")
                    bannerState.loadAd(adRequest)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = !isLoading,
            ) {
                Text(if (isLoading) "Loading..." else "Load Banner")
            }

            LogSection(logs = logs, modifier = Modifier.fillMaxWidth())

            Banner(
                state = bannerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            )
        }
    }
}
