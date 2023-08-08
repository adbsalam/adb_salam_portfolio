package uk.adbsalam.portfolio.reviews.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.reviews.feature.components.reviewcard.ReviewCardPreviewLight
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class ReviewsCardComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun `ReviewCard - light mode`() {
        paparazzi.captureScreenshot {
            ReviewCardPreviewLight()
        }
    }
}



