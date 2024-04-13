package com.company.dolshop.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// onSurface : Text 색상
// primary : RadioButton 클릭 색상 /
// OnSurfaceVariant : RadioButton 클릭 안 되었을 때 색상 /
// secondaryContainer : BottomNavItem 클릭 시 컨테이너 색상

private val LightColors = lightColorScheme(
    primary = lightPrimary,
    onPrimary = custom2,
    primaryContainer = custom1,
    onPrimaryContainer = custom1,
    secondary = lightSecondary,
    onSecondary = custom1,
    secondaryContainer = custom1,
    onSecondaryContainer = custom1,
    tertiary = custom1,
    onTertiary = custom1,
    tertiaryContainer = custom1,
    onTertiaryContainer = custom1,
    error = custom1,
    errorContainer = custom1,
    onError = custom1,
    onErrorContainer = custom1,
    background = lightBackGround,
    onBackground = custom1,
    surface = custom1,
    onSurface = lightText,
    surfaceVariant = custom1,
    onSurfaceVariant = lightOnSurfaceVariant,
    outline = custom1,
    inverseOnSurface = custom1,
    inverseSurface = custom1,
    inversePrimary = custom1,
    surfaceTint = custom1,
    outlineVariant = custom1,
    scrim = custom1
)

private val DarkColors = darkColorScheme(
    primary = darkPrimary,
    onPrimary = custom2,
    primaryContainer = custom6,
    onPrimaryContainer = custom1,
    secondary = darkSecondary,
    onSecondary = custom1,
    secondaryContainer = custom1,
    onSecondaryContainer = custom1,
    tertiary = darkBackGround,
    onTertiary = custom1,
    tertiaryContainer = custom1,
    onTertiaryContainer = custom1,
    error = custom1,
    errorContainer = custom1,
    onError = custom1,
    onErrorContainer = custom1,
    background = darkBackGround,
    onBackground = custom1,
    surface = custom1,
    onSurface = darkText,
    surfaceVariant = custom1,
    onSurfaceVariant = darkOnSurfaceVariant,
    outline = custom1,
    inverseOnSurface = custom1,
    inverseSurface = custom1,
    inversePrimary = custom1,
    surfaceTint = custom1,
    outlineVariant = custom1,
    scrim = custom1
)

@Composable
fun DolShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    typography : androidx.compose.material3.Typography = Typography,
    content: @Composable () -> Unit
) {
    val colors = if (!darkTheme) {
        LightColors
    } else {
        DarkColors
    }
    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = typography,
    )
}