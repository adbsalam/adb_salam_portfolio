package uk.adbsalam.portfolio.home.feature

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.home.feature.components.card.LottiInfoCardDarkPreview
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class LottiCardDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun lottiInfoCardDarkPreviewSnapTest() {
        paparazzi.captureScreenshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                LottiInfoCardDarkPreview()
            }
        }
    }
}



