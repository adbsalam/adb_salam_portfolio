package uk.adbsalam.portfolio.home.feature.components.card

import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
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
            .adbRoundedBackground()
    ) {

        if (LocalInspectionMode.current) {
            Image(
                painter = painterResource(id = R.drawable.preview),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        } else {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

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
@PreviewLight
@SnapIt(name = "LottiInfoCard - Light Mode", preview = true)
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
@PreviewDark
@SnapIt(
    name = "LottiInfoCard - Dark Mode",
    isDark = true,
    preview = true
)
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
