package uk.adbsalam.portfolio.home.feature.components.card

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import uk.adbsalam.portfolio.home.feature.HomeScreenItem
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param tags tags list to show chips for
 * @param title title text to be set
 * @param body body or description of card component
 * @param resId resource ID to be used to set image
 * @param action action to perform on View button is clicked
 */
@Composable
internal fun InfoCard(
    tags: List<String>,
    title: String,
    body: String,
    @DrawableRes resId: Int,
    action: () -> Unit,
) {
    val readMore = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .adbRoundedBackground()
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
            tags = tags,
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
            tags = HomeScreenItem.createMock().first().tags,
            title = "This Is Sample Title",
            body = "This is some very long text that will show some description of what item is and what it does",
            resId = R.drawable.preview,
            action = {/* unused */ }
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@SnapIt(name = "InfoCard - Dark Mode")
internal fun InfoCardDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        InfoCard(
            tags = HomeScreenItem.createMock().first().tags,
            title = "This Is Sample Title",
            body = "This is some very long text that will show some description of what item is and what it does",
            resId = R.drawable.preview,
            action = {/* unused */ }
        )
    }
}