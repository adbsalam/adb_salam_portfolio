package uk.adbsalam.portfolio.gallery.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.gallery.feature.fullscreen.FullscreenGalleryLightPreview
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class FullscreenGalleryComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun fullscreenGalleryLightPreviewSnapTest() {
        paparazzi.captureScreenshot {
            FullscreenGalleryLightPreview()
        }
    }
}



