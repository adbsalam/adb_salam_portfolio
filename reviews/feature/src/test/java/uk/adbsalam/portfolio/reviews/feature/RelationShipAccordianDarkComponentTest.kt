package uk.adbsalam.portfolio.reviews.feature

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.adbsalam.portfolio.reviews.feature.components.reviewcard.RelationShipAccordionPreviewDark
import uk.adbsalam.snapit.testing.captureScreenshot
import uk.adbsalam.snapit.testing.forDarkComponent

@RunWith(JUnit4::class)
class RelationShipAccordianDarkComponentTest {
    @get:Rule
    val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

    @Test
    fun relationShipAccordionPreviewDarkSnapTest() {
        paparazzi.captureScreenshot {
            RelationShipAccordionPreviewDark()
        }
    }
}



