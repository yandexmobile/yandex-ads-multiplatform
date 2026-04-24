package com.yandex.ads.sample.kmp.ui.appopen

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
import com.yandex.mobile.ads.kmp.appopenad.AppOpenAd
import com.yandex.mobile.ads.kmp.compose.rememberAppOpenAdLoader
import com.yandex.mobile.ads.kmp.common.AdRequest
import com.yandex.ads.sample.kmp.data.NetworkProvider
import com.yandex.ads.sample.kmp.ui.components.LogSection
import com.yandex.ads.sample.kmp.ui.components.SampleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun AppOpenAdScreen(navController: NavHostController) {
    val networks = NetworkProvider.appOpenNetworks
    val adUnitId = networks.first().adUnitId
    var logs by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var appOpenAd by remember { mutableStateOf<AppOpenAd?>(null) }
    val loader = rememberAppOpenAdLoader()
    val scope = rememberCoroutineScope()

    fun appendLog(msg: String) {
        logs = if (logs.isEmpty()) msg else "$logs\n$msg"
    }

    Scaffold(
        topBar = {
            SampleTopAppBar(title = "App Open Ad", onBack = { navController.popBackStack() })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Text("Network: Yandex")

            Button(
                onClick = {
                    isLoading = true
                    appendLog("Loading app open ad...")
                    scope.launch {
                        try {
                            val request = AdRequest(adUnitId = adUnitId)
                            val ad = loader.loadAd(request)
                            appOpenAd = ad
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
                Text(if (isLoading) "Loading..." else "Load App Open")
            }

            Button(
                onClick = {
                    appOpenAd?.show()
                    appendLog("Ad shown")
                    appOpenAd = null
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                enabled = appOpenAd != null,
            ) {
                Text("Show App Open")
            }

            LogSection(logs = logs, modifier = Modifier.fillMaxWidth())
        }
    }
}
