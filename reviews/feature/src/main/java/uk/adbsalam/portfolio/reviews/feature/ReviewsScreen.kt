package uk.adbsalam.portfolio.reviews.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.reviews.data.ReviewsData
import uk.adbsalam.portfolio.reviews.feature.components.ReviewLottiTitle
import uk.adbsalam.portfolio.reviews.feature.components.reviewcard.ReviewCard
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun ReviewsScreen(
    reviews: List<ReviewsData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        ReviewLottiTitle()

        reviews.forEach {
            ReviewCard(review = it)
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview
@Composable
fun ReviewsScreenPreviewLight() {
    Adb_Theme {
        ReviewsScreen(
            reviews = ReviewsData.createMock()
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReviewsScreenPreviewDark() {
    Adb_Theme(true) {
        ReviewsScreen(
            reviews = ReviewsData.createMock()
        )
    }
}