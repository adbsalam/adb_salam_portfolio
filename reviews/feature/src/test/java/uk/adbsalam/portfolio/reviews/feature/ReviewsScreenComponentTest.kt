package uk.adbsalam.portfolio.reviews.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class ReviewsScreenComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun `ReviewsScreen - light mode`() {
        paparazzi.captureScreenshot {
            ReviewsScreenPreviewLight()
        }
    }
}



