package uk.adbsalam.portfolio.walkie.feature

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import uk.adbsalam.portfolio.communication.CommunicationsComponentActivity
import uk.adbsalam.portfolio.theming.appbackground.AppGradientContainer
import uk.adbsalam.portfolio.walkie.feature.connect.Connect

@AndroidEntryPoint
class WalkieActivity : CommunicationsComponentActivity() {

    private val viewModel: WalkieActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handlePermissions(
            onPermissionsGranted = viewModel::onStartConnection,
            onPermissionsError = viewModel::onPermissionsError,
            onRequirePermissions = viewModel::onRequirePermission
        )

        setContent {
            val uiState by viewModel.viewState.collectAsState()

            AppGradientContainer(
                theme = viewModel.currentTheme,
                dynamicColor = viewModel.isDynamic
            ) {
                WalkieHome(uiState = uiState)
            }
        }
    }

    @Composable
    private fun WalkieHome(
        uiState: WalkieState
    ) {
        when (uiState) {
            WalkieState.OnLoading -> {}
            WalkieState.OnPermissionsError ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Something went wrong, please retry!",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            WalkieState.OnRequirePermissions ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Missing Permissions",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            WalkieState.OnConnect ->
                Connect()
        }
    }
}

