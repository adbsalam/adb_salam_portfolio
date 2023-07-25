package uk.adbsalam.portfolio.components

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.components.images.AdbImagePreview
import uk.adbsalam.portfolio.components.images.AdbMainLogo
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class ImageComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun `AdbImage - should render correctly`() {
        paparazzi.captureScreenshot {
            AdbImagePreview()
        }
    }

    @Test
    fun `AdbMainLogo - should render correctly`() {
        paparazzi.captureScreenshot {
            AdbMainLogo()
        }
    }
}



