package uk.adbsalam.portfolio.home.feature.components.card

import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.home.feature.HomeScreenItem
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param tags tags list to show chips for
 * @param title title text to be set
 * @param body body or description of card component
 * @param resId resource ID to be used to set image
 * @param action action to perform on View button is clicked
 */
@Composable
internal fun LottiInfoCard(
    @RawRes resId: Int,
    tags: List<String>,
    title: String,
    body: String,
    maxIteration: Int = Integer.MAX_VALUE,
    action: () -> Unit,
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = maxIteration,
        isPlaying = true
    )

    val readMore = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
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
@SnapIt(name = "LottiInfoCard - Light Mode")
internal fun LottiInfoCardLightPreview() {
    Adb_Theme {
        LottiInfoCard(
            resId = R.raw.lotti_app_patrolla,
            tags = HomeScreenItem.createMock().first().tags,
            title = "Patrolla Android App",
            body = "This is some body of this view",
            action = {/* unused */ }
        )
    }
}

@Composable
@Preview
@SnapIt(name = "LottiInfoCard - Dark Mode")
internal fun LottiInfoCardDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        LottiInfoCard(
            resId = R.raw.lotti_app_patrolla,
            tags = HomeScreenItem.createMock().first().tags,
            title = "Patrolla Android App",
            body = "This is some body of this view",
            action = { /* unused */ }
        )
    }
}
