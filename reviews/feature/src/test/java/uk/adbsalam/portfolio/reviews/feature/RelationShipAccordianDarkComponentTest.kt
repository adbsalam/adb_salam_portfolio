 package uk.adbsalam.portfolio.reviews.feature.components.reviewcard

 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureDarkScreenshot
 import uk.adbsalam.snapit.testing.forDarkComponent

 @RunWith(JUnit4::class)
 class RelationShipAccordianDarkComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

   @Test
   fun `RelationShipAccordion - dark mode`() {
     paparazzi.captureDarkScreenshot {
         RelationShipAccordionPreviewDark()
     }
   }
 }



