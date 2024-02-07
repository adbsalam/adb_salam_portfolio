package uk.adbsalam.portfolio.walkie.feature.connect

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.Payload
import uk.adbsalam.portfolio.communication.NearbyUsers
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.walkie.feature.communication.Communication

@Composable
fun Connect(
    viewModel: ConnectViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val connectionClient = Nearby.getConnectionsClient(activity)
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = null) { viewModel.initCommunications(connectionClient) }

    Connect(
        uiState = uiState,
        currentUserChannel = viewModel.currentUserChannel,
        onUserClick = viewModel::connectToUser,
        startRecording = viewModel::sendAudio
    )

    DisposableEffect(null) {
        onDispose {
            viewModel.endConnection()
        }
    }
}

@Composable
private fun Connect(
    uiState: ConnectState,
    currentUserChannel: String,
    onUserClick: (NearbyUsers.User) -> Unit,
    startRecording: (Payload) -> Unit
) {
    when (uiState) {
        ConnectState.OnLoading -> {}
        is ConnectState.OnConnected -> Communication(
            userName = currentUserChannel,
            sendAudio = startRecording,
            audioPayload = uiState.payload
        )

        is ConnectState.OnUpdateLogs -> {
            Connect(
                log = uiState.log,
                currentUserChannel = currentUserChannel,
                nearbyUser = uiState.nearbyUsers,
                onClick = onUserClick
            )
        }
    }
}

@Composable
private fun Connect(
    log: String,
    currentUserChannel: String,
    nearbyUser: NearbyUsers,
    onClick: (NearbyUsers.User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(10.dp)
    ) {

        if (nearbyUser.users.size > 0) {
            Text(text = "Select channel to connect", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
        }

        LazyColumn(
            modifier = Modifier.weight(2f),
            content = {
                nearbyUser.users.forEach {
                    item {
                        UserItem(user = it, onClick = { onClick(it) })
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.searching_nearby))
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = Integer.MAX_VALUE,
                isPlaying = true
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
                Text(
                    text = "Channel: $currentUserChannel",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = log, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@PreviewLight
@Composable
private fun ConnectPreview() {
    Adb_Screen_Theme {
        Connect(
            currentUserChannel = "23123",
            onUserClick = {},
            startRecording = {},
            uiState = ConnectState.OnUpdateLogs(
                log = "Loading Users...",
                nearbyUsers = NearbyUsers.mockUsers()
            )
        )
    }
}