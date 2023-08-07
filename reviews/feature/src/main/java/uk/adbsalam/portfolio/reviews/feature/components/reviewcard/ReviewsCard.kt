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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.reviews.feature.ReviewsData
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@Composable
fun ReviewCard(
    review: ReviewsData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .adbRoundedBackground()
            .animateContentSize()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ReviewTitle(review = review)

        Text(
            text = review.review,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Divider(modifier = Modifier.fillMaxWidth())

        RelationShipAccordian(review = review)
    }
}

@Preview
@Composable
fun ReviewCardPreview() {
    ReviewCard(review = ReviewsData.createMock().first())
}