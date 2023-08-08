package uk.adbsalam.portfolio.info.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.info.feature.components.charts.CircularChartPreviewDark
import uk.adbsalam.snapit.testing.captureDarkScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class CircularChartDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun `CircularChart - dark mode`() {
        paparazzi.captureDarkScreenshot {
            CircularChartPreviewDark()
        }
    }
}



