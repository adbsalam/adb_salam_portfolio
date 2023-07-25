package uk.adbsalam.portfolio.startup.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import uk.adbsalam.portfolio.startup.feature.components.LoadingScreen
import uk.adbsalam.portfolio.startup.feature.components.ThemeButtons
import uk.adbsalam.portfolio.theming.Adb_Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: StartupViewModel = hiltViewModel()
            val theme by mainViewModel.currentTheme.collectAsState()
            val dynamic by mainViewModel.dynamic.collectAsState()
            val uiState by mainViewModel.uiState.collectAsState()

            LaunchedEffect(key1 = null){
                mainViewModel.initApp()
            }

            Adb_Theme(
                themeType = theme,
                dynamic = dynamic
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {

                    when (uiState) {
                        StartupState.OnLoading -> LoadingScreen()
                        StartupState.OnStart -> ThemeButtons(
                            onDark = mainViewModel::onDark,
                            onLight = mainViewModel::onLight,
                            onDynamic = mainViewModel::onDynamic,
                            onSystem = mainViewModel::onSystem
                        )
                    }
                }
            }
        }
    }
}
