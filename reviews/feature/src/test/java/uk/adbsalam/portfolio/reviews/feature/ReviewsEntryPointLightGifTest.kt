package uk.adbsalam.portfolio.reviews.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.forGif
import uk.adbsalam.snapit.testing.gifSnapshot

@RunWith(JUnit4::class)
class ReviewsEntryPointLightGifTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forGif()

    @Test
    fun reviewsPreviewLightSnapTest() {
        paparazzi.gifSnapshot(0L, 1000L, 30) {
            ReviewsPreviewLight()
        }
    }
}