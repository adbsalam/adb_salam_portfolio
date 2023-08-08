 package uk.adbsalam.portfolio.info.feature.components.charts

 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureScreenshot
 import uk.adbsalam.snapit.testing.forComponent

 @RunWith(JUnit4::class)
 class HorizontalChartComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun `HorizontalChart - light mode`() {
     paparazzi.captureScreenshot {
         HorizontalChartPreviewLight()
     }
   }
 }



