package uk.adbsalam.portfolio.info.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.info.feature.components.charts.HorizontalChartPreviewDark
import uk.adbsalam.snapit.testing.captureDarkScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class HorizontalChartDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun `HorizontalChart - dark mode`() {
        paparazzi.captureDarkScreenshot {
            HorizontalChartPreviewDark()
        }
    }
}



