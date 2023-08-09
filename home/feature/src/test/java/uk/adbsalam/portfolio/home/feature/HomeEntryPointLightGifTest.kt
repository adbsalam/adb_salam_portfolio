package uk.adbsalam.portfolio.home.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.forGif
import uk.adbsalam.snapit.testing.gifSnapshot

@RunWith(JUnit4::class)
class HomeEntryPointLightGifTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forGif()

    @Test
    fun homePreviewLightSnapTest() {
        paparazzi.gifSnapshot(0L, 1000L, 30) {
            HomePreviewLight()
        }
    }
}



