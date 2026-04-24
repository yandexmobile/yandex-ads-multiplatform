package com.yandex.ads.sample.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
