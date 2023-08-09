package uk.adbsalam.portfolio.reviews.feature

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems
import uk.adbsalam.portfolio.reviews.feature.components.ReviewLottiTitle
import uk.adbsalam.portfolio.reviews.feature.components.reviewcard.ReviewCard
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param reviews reviews to show on screen
 * Main screen to populate reviews on screen
 */
@Composable
internal fun ReviewsScreen(
    reviews: ReviewItems
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        ReviewLottiTitle()

        reviews.reviews.forEach {
            ReviewCard(review = it)
        }

        Spacer(modifier = Modifier.height(30.dp))
    }

    if (scrollState.value > 0) {
        BackHandler {
            scope.launch {
                scrollState.animateScrollTo(0)
            }
        }
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun ReviewsScreenPreviewLight() {
    Adb_Screen_Theme {
        ReviewsScreen(
            reviews = ReviewItems.createMock()
        )
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun ReviewsScreenPreviewDark() {
    Adb_Screen_Theme(isDark = true) {
        ReviewsScreen(
            reviews = ReviewItems.createMock()
        )
    }
}