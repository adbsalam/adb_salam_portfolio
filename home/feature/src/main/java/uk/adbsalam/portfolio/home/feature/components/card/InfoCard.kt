package uk.adbsalam.portfolio.home.feature.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
internal fun InfoCard(
    imageHint: String,
    title: String,
    body: String,
    @DrawableRes resId: Int,
    action: () -> Unit,
) {

    val readMore = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
        )

        CardInfoText(
            imageHint = imageHint,
            title = title,
            body = body,
            readMore = readMore,
            action = action
        )
    }
}


@Composable
@Preview
@SnapIt(name = "InfoCard - Light Mode")
internal fun InfoCardLightPreview() {
    Adb_Theme {
        InfoCard(
            imageHint = "Sample",
            title = "This Is Sample Title",
            body = "This is some very long text that will show some description of what item is and what it does",
            resId = R.drawable.preview,
            action = {/* unused */ }
        )
    }
}

@Composable
@Preview
@SnapIt(name = "InfoCard - Dark Mode")
internal fun InfoCardDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        InfoCard(
            imageHint = "Sample",
            title = "This Is Sample Title",
            body = "This is some very long text that will show some description of what item is and what it does",
            resId = R.drawable.preview,
            action = {/* unused */ }
        )
    }
}