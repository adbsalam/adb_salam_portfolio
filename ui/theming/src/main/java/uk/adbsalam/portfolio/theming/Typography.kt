package uk.adbsalam.portfolio.theming

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


val appFont = FontFamily(
    Font(
        R.font.monotserrat_regular,
        weight = FontWeight.W400,
    ),

    Font(
        R.font.monotserrat_medium,
        weight = FontWeight.W500,
    ),

    Font(
        R.font.monotserrat_semibold,
        weight = FontWeight.W600,
    ),

    Font(
        R.font.monotserrat_bold,
        weight = FontWeight.W700,
    ),
)
