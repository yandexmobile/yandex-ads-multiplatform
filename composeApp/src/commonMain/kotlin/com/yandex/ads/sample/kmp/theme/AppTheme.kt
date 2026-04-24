package com.yandex.ads.sample.kmp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00B4FF),
    secondary = Color(0xFF00C9FF),
    tertiary = Color(0xFF00E5FF),
    background = Color(0xFF0D1117),
    surface = Color(0xFF161B22),
    error = Color(0xFFFF6B6B),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0078D4),
    secondary = Color(0xFF1084D7),
    tertiary = Color(0xFF4DB8FF),
    background = Color(0xFFFAFBFC),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFD1293D),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
