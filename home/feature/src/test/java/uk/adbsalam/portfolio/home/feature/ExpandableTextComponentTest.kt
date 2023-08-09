package uk.adbsalam.portfolio.home.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.home.feature.components.card.ExpandableTextCollapsedPreview
import uk.adbsalam.portfolio.home.feature.components.card.ExpandableTextExpandedPreview
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forComponent

@RunWith(JUnit4::class)
class ExpandableTextComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forComponent()

    @Test
    fun expandableTextCollapsedPreviewSnapTest() {
        paparazzi.captureScreenshot {
            ExpandableTextCollapsedPreview()
        }
    }

    @Test
    fun expandableTextExpandedPreviewSnapTest() {
        paparazzi.captureScreenshot {
            ExpandableTextExpandedPreview()
        }
    }
}



