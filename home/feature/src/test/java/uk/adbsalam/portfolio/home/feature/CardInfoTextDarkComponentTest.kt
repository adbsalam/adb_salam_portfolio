package uk.adbsalam.portfolio.home.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.home.feature.components.card.CardInfoTextPreviewDark
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class CardInfoTextDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun cardInfoTextPreviewDarkSnapTest() {
        paparazzi.captureScreenshot {
            CardInfoTextPreviewDark()
        }
    }
}



