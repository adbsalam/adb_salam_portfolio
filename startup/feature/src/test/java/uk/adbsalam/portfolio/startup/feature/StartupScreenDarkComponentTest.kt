 package uk.adbsalam.portfolio.startup.feature

 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureDarkScreenshot
 import uk.adbsalam.snapit.testing.forDarkComponent

 @RunWith(JUnit4::class)
 class StartupScreenDarkComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forDarkComponent()

   @Test
   fun `StartupScreen - when in dark mode`() {
     paparazzi.captureDarkScreenshot {
         StartupScreenLoadingDarkTheme()
     }
   }
 }



