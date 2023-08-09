package uk.adbsalam.portfolio.info.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.forGif
import uk.adbsalam.snapit.testing.gifSnapshot

@RunWith(JUnit4::class)
class InfoEntryPointLightGifTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forGif()

    @Test
    fun infoScreenLightPreviewSnapTest() {
        paparazzi.gifSnapshot(0L, 1000L, 30) {
            InfoScreenLightPreview()
        }
    }
}



