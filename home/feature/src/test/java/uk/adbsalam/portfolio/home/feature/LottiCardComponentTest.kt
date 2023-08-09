package uk.adbsalam.portfolio.home.feature

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.home.feature.components.card.LottiInfoCardLightPreview
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class LottiCardComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun lottiInfoCardLightPreviewSnapTest() {
        paparazzi.captureScreenshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                LottiInfoCardLightPreview()
            }
        }
    }
}



