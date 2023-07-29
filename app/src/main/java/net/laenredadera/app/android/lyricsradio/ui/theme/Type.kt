package net.laenredadera.app.android.lyricsradio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import net.laenredadera.app.android.lyricsradio.R

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val eczarFont = GoogleFont("Eczar")
val RobotoFont = GoogleFont("Roboto Condensed")
val LatoFont = GoogleFont("Lato")

val fontFamilyEczar = FontFamily(
    Font(googleFont = eczarFont, fontProvider = provider)
)
val fontFamilyRoboto = FontFamily(
    Font(googleFont = RobotoFont, fontProvider = provider)
)
val fontFamilyLato = FontFamily(
    Font(googleFont = LatoFont, fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamilyLato ,
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)