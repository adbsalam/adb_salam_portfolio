package uk.adbsalam.portfolio.startup.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forDarkScreen

@RunWith(JUnit4::class)
class StartupScreenDarkScreenTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkScreen()

    @Test
    fun startupScreenLoadingDarkThemeSnapTest() {
        paparazzi.captureScreenshot {
            StartupScreenLoadingDarkTheme()
        }
    }
}



