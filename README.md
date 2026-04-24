# Yandex Mobile Ads KMP SDK

Kotlin Multiplatform plugin for the Yandex Ads SDK. Allows KMP developers to integrate Yandex ads into Android and iOS apps using Compose Multiplatform.

## Documentation

Documentation is available on the [official website][DOCUMENTATION].

## License

EULA is available at the [EULA website][LICENSE].

## Integration

### 1. Add the Gradle dependency

In your shared module's `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("com.yandex.ads.multiplatform:mobileads-compose:<version>")
}
```

### 2. Configure iOS dependencies

Add the `YandexMobileAds` pod to your `iosApp/Podfile`:

```ruby
source 'https://cdn.cocoapods.org/'

platform :ios, '13.0'

target 'iosApp' do
  use_frameworks!
  pod 'YandexMobileAds', '<version>'
end
```

Then install pods:

```shell
cd iosApp && pod install
```

### 3. Initialize the SDK

Call `YandexAds.initialize()` before loading any ads:

```kotlin
import com.yandex.mobile.ads.kmp.YandexAds

@Composable
fun App() {
    LaunchedEffect(Unit) {
        YandexAds.initialize()
    }
    // ...
}
```

## Usage

### Banner Ad

```kotlin
import com.yandex.mobile.ads.kmp.banner.Banner
import com.yandex.mobile.ads.kmp.banner.BannerAdSize
import com.yandex.mobile.ads.kmp.banner.BannerEvents
import com.yandex.mobile.ads.kmp.common.AdRequest

Banner(
    adRequest = AdRequest(adUnitId = "your-ad-unit-id"),
    adSize = BannerAdSize.Inline(width = 320, maxHeight = 50),
    modifier = Modifier.fillMaxWidth(),
    events = BannerEvents(
        onAdLoaded = { /* ad loaded */ },
        onAdFailedToLoad = { error -> /* handle error */ },
        onAdClicked = { /* ad clicked */ },
        onImpression = { data -> /* impression tracked */ },
    ),
)
```

### Interstitial Ad

```kotlin
import com.yandex.mobile.ads.kmp.compose.rememberInterstitialAdLoader
import com.yandex.mobile.ads.kmp.common.AdRequest

val loader = rememberInterstitialAdLoader()
val scope = rememberCoroutineScope()

// Load
scope.launch {
    try {
        val ad = loader.loadAd(AdRequest(adUnitId = "your-ad-unit-id"))
        // Show when ready
        ad.show()
    } catch (e: Exception) {
        // handle error
    }
}
```

### Rewarded Ad

```kotlin
import com.yandex.mobile.ads.kmp.compose.rememberRewardedAdLoader
import com.yandex.mobile.ads.kmp.common.AdRequest

val loader = rememberRewardedAdLoader()
val scope = rememberCoroutineScope()

// Load
scope.launch {
    try {
        val ad = loader.loadAd(AdRequest(adUnitId = "your-ad-unit-id"))
        // Show when ready
        ad.show()
    } catch (e: Exception) {
        // handle error
    }
}
```

### App Open Ad

```kotlin
import com.yandex.mobile.ads.kmp.compose.rememberAppOpenAdLoader
import com.yandex.mobile.ads.kmp.common.AdRequest

val loader = rememberAppOpenAdLoader()
val scope = rememberCoroutineScope()

// Load
scope.launch {
    try {
        val ad = loader.loadAd(AdRequest(adUnitId = "your-ad-unit-id"))
        // Show when ready
        ad.show()
    } catch (e: Exception) {
        // handle error
    }
}
```

## Policies

Configure user consent before loading ads:

```kotlin
// GDPR user consent
YandexAds.setUserConsent(true)

// Location tracking
YandexAds.setLocationTracking(true)

// COPPA age restriction
YandexAds.setAgeRestricted(false)
```

## Sample app

This project is a working sample demonstrating all ad formats. To run it:

**Android:**
```shell
./gradlew composeApp:assembleDebug
```

**iOS:**
```shell
cd iosApp && pod install && open iosApp.xcworkspace
```

Then build and run from Xcode (⌘R).

[DOCUMENTATION]: https://yandex.com/dev/mobile-ads/doc/intro/about.html
[LICENSE]: https://yandex.com/legal/mobileads_sdk_agreement/
