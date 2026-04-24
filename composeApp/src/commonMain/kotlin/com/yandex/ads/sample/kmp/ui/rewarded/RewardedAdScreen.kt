package com.yandex.ads.sample.kmp.ui.rewarded

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yandex.mobile.ads.kmp.common.AdRequest
import com.yandex.mobile.ads.kmp.compose.rememberRewardedAdLoader
import com.yandex.mobile.ads.kmp.rewarded.RewardedAd
import com.yandex.mobile.ads.kmp.rewarded.RewardedAdEventListener
import com.yandex.ads.sample.kmp.data.NetworkProvider
import com.yandex.ads.sample.kmp.ui.components.AdUnitIdSelector
import com.yandex.ads.sample.kmp.ui.components.LogSection
import com.yandex.ads.sample.kmp.ui.components.SampleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun RewardedAdScreen(navController: NavHostController) {
    val networks = NetworkProvider.rewardedNetworks
    var adUnitId by remember { mutableStateOf(networks.first().adUnitId) }
    var logs by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var rewardedAd by remember { mutableStateOf<RewardedAd?>(null) }
    val loader = rememberRewardedAdLoader()
    val scope = rememberCoroutineScope()

    fun appendLog(msg: String) {
        logs = if (logs.isEmpty()) msg else "$logs\n$msg"
    }

    Scaffold(
        topBar = {
            SampleTopAppBar(title = "Rewarded Ad", onBack = { navController.popBackStack() })
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
                    appendLog("Loading rewarded ad...")
                    scope.launch {
                        try {
                            val request = AdRequest(adUnitId = adUnitId)
                            val ad = loader.loadAd(request)
                            rewardedAd = ad
                            isLoading = false
                            appendLog("Ad loaded - click 'Show' to display")
                        } catch (e: Exception) {
                            isLoading = false
                            appendLog("Load failed: ${e.message}")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = !isLoading,
            ) {
                Text(if (isLoading) "Loading..." else "Load Rewarded")
            }

            Button(
                onClick = {
                    rewardedAd?.show()
                    appendLog("Ad shown")
                    rewardedAd = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                enabled = rewardedAd != null,
            ) {
                Text("Show Rewarded")
            }

            LogSection(logs = logs, modifier = Modifier.fillMaxWidth())
        }
    }
}
