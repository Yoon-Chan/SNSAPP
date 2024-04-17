package com.example.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.example.presentation.*


private val pretendardFontFamily = FontFamily(Font(R.font.pretendardvariable))
// Set of Material typography styles to start with

private val defaultTypography = Typography()

val Typography = Typography(
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = pretendardFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = pretendardFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = pretendardFontFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = pretendardFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = pretendardFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = pretendardFontFamily),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = pretendardFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = pretendardFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = pretendardFontFamily),
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = pretendardFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = pretendardFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = pretendardFontFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = pretendardFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = pretendardFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = pretendardFontFamily),
)
//val Typography =
//    Typography(
//        bodyLarge =
//            TextStyle(
//                fontFamily = FontFamily.Default,
//                fontWeight = FontWeight.Normal,
//                fontSize = 16.sp,
//                lineHeight = 24.sp,
//                letterSpacing = 0.5.sp,
//            ),
/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
 */
//)
