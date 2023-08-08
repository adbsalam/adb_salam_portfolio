package uk.adbsalam.portfolio.videos.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.videos.feature.components.VideoCardPreviewDark
import uk.adbsalam.snapit.testing.captureDarkScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class VideoCardDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun `VideoCard - dark mode`() {
        paparazzi.captureDarkScreenshot {
            VideoCardPreviewDark()
        }
    }
}



