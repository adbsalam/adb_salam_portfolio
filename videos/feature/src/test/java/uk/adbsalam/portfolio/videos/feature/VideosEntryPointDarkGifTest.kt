package uk.adbsalam.portfolio.videos.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.snapit.testing.forDarkGif
import uk.adbsalam.snapit.testing.gifSnapshot

@RunWith(JUnit4::class)
class VideosEntryPointDarkGifTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkGif()

    @Test
    fun videosPreviewDarkSnapTest() {
        paparazzi.gifSnapshot(0L, 1000L, 30) {
            VideosPreviewDark()
        }
    }
}



