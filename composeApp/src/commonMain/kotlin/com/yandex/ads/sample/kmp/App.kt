package com.yandex.ads.sample.kmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yandex.mobile.ads.kmp.YandexAds
import com.yandex.ads.sample.kmp.theme.AppTheme
import com.yandex.ads.sample.kmp.ui.appopen.AppOpenAdScreen
import com.yandex.ads.sample.kmp.ui.banner.BannerAdScreen
import com.yandex.ads.sample.kmp.ui.home.HomeScreen
import com.yandex.ads.sample.kmp.ui.interstitial.InterstitialAdScreen
import com.yandex.ads.sample.kmp.ui.policies.PoliciesScreen
import com.yandex.ads.sample.kmp.ui.rewarded.RewardedAdScreen

@Composable
fun App() {
    AppTheme {
        LaunchedEffect(Unit) {
            YandexAds.initialize()
        }
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("banner") { BannerAdScreen(navController) }
            composable("interstitial") { InterstitialAdScreen(navController) }
            composable("rewarded") { RewardedAdScreen(navController) }
            composable("appopen") { AppOpenAdScreen(navController) }
            composable("policies") { PoliciesScreen(navController) }
        }
    }
}
