package uk.adbsalam.portfolio.home.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.home.feature.components.card.InfoCardDarkPreview
import uk.adbsalam.snapit.testing.captureDarkScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class InfoCardDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun `InfoCard - Dark Mode`() {
        paparazzi.captureDarkScreenshot {
            InfoCardDarkPreview()
        }
    }
}



