 package uk.adbsalam.portfolio.settings.feature

 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureScreenshot
 import uk.adbsalam.snapit.testing.forComponent

 @RunWith(JUnit4::class)
 class SettingsComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun `SettingsScreen - light mode`() {
     paparazzi.captureScreenshot {
         SettingsScreenLightPreview()
     }
   }
 }



