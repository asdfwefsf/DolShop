package com.company.dolshop.designsystem

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.company.presentation.R


private val spoqaBold = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold , FontWeight.Bold)
)

private val spoqaRegular = FontFamily(
    Font(R.font.spoqa_han_sans_neo_regular , FontWeight.Normal)
)

private val spoqaThin = FontFamily(
    Font(R.font.spoqa_han_sans_neo_thin , FontWeight.Thin)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 60.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 32.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = spoqaThin,
        fontSize = 24.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = spoqaThin,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 18.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 18.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 15.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 14.sp
    )
)
val Typography.custom1 : TextStyle
    @Composable get() = displayLarge.copy(
        fontSize = 30.sp
    )
val Typography.custom2 : TextStyle
    @Composable get() = displayMedium.copy(
        fontSize = 40.sp
    )
val Typography.custom3 : TextStyle
    @Composable get() = displaySmall.copy(
        fontSize = 50.sp
    )