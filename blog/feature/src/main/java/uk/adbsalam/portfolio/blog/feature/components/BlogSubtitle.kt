package uk.adbsalam.portfolio.blog.feature.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.theming.appFont

@Composable
fun BlogSubTitle(
    text: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        fontSize = 14.sp,
        fontFamily = appFont,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W400
    )
}

@Preview
@Composable
fun BlogSubtitlePreview() {
    BlogSubTitle(text = "This is a subtitle")
}