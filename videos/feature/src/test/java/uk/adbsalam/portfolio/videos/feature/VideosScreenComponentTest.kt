package uk.adbsalam.portfolio.videos.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class VideosScreenComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun videosScreenPreviewLightSnapTest() {
        paparazzi.captureScreenshot {
            VideosScreenPreviewLight()
        }
    }
}



