package uk.adbsalam.portfolio.reviews.feature.components.reviewcard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param review reviews data to be populated on ReviewCard
 * This is main Reviews card to display in list
 */
@Composable
internal fun ReviewCard(
    review: ReviewItems.Review
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .adbRoundedBackground()
            .animateContentSize()
            .padding(vertical = 12.dp),
    ) {
        ReviewTitle(review = review)

        Text(
            text = review.review,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Divider(modifier = Modifier.fillMaxWidth())

        RelationShipAccordion(review = review)
    }
}

@PreviewLight
@Composable
@SnapIt(name = "ReviewCard - light mode")
internal fun ReviewCardPreviewLight() {
    Adb_Theme {
        ReviewCard(review = ReviewItems.createMock().reviews.first())
    }
}

@PreviewDark
@Composable
@SnapIt(name = "ReviewCard - dark mode", isDark = true)
internal fun ReviewCardPreviewDark() {
    Adb_Theme(isSystemDark = true) {
        ReviewCard(review = ReviewItems.createMock().reviews.first())
    }
}