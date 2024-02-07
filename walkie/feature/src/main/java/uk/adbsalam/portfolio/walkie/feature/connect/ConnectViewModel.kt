package uk.adbsalam.portfolio.walkie.feature.connect

import androidx.lifecycle.ViewModel
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.Payload
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.adbsalam.portfolio.communication.CommunicationsManager
import uk.adbsalam.portfolio.communication.ConnectionState
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionFailure
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionInitiated
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionSuccess
import uk.adbsalam.portfolio.communication.ConnectionState.OnDisconnected
import uk.adbsalam.portfolio.communication.ConnectionState.OnEndpointFound
import uk.adbsalam.portfolio.communication.ConnectionState.OnEndpointLost
import uk.adbsalam.portfolio.communication.ConnectionState.OnLog
import uk.adbsalam.portfolio.communication.NearbyUsers
import uk.adbsalam.portfolio.communication.PayloadState
import uk.adbsalam.portfolio.communication.PayloadState.OnPayloadFailure
import uk.adbsalam.portfolio.communication.PayloadState.OnPayloadReceived
import uk.adbsalam.portfolio.communication.PayloadState.OnPayloadTransferUpdate
import uk.adbsalam.portfolio.walkie.feature.connect.ConnectState.OnConnected
import uk.adbsalam.portfolio.walkie.feature.connect.ConnectState.OnUpdateLogs
import javax.inject.Inject


@HiltViewModel
class ConnectViewModel @Inject constructor(
    private val commsManager: CommunicationsManager
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<ConnectState>(ConnectState.OnLoading)
    internal val viewState = _viewState.asStateFlow()
    private val nearbyUsers = NearbyUsers()
    var currentUserChannel = ""


    private lateinit var connectedUser: NearbyUsers.User
    private var connectedUserId = ""

    fun initCommunications(connectionClient: ConnectionsClient) {
        currentUserChannel = (0..9999).random().toString().take(4)
        commsManager.initCommunications(
            connectionClient = connectionClient,
            username = currentUserChannel,
            onStateUpdate = ::handleConnectionState
        )
    }

    private fun updateHomeState(log: String) {
        _viewState.value = OnUpdateLogs(log, nearbyUsers)
    }

    fun connectToUser(user: NearbyUsers.User) {
        connectedUser = user
        commsManager.connectToEndpoint(
            user = user,
            onStateUpdate = ::handleConnectionState
        )
    }

    private fun handleConnectionState(state: ConnectionState) {
        when (state) {
            OnConnectionFailure -> updateHomeState("Connection failed")
            is OnConnectionSuccess -> handleSuccess(state.connectedUserId)
            is OnLog -> updateHomeState(state.log)
            is OnDisconnected -> {}
            is OnEndpointLost -> {}
            is OnConnectionInitiated -> {
                updateHomeState("Connection initiated")
                commsManager.acceptConnection(state.endpointId, ::handlePayload)
            }

            is OnEndpointFound -> {
                val isAlreadyExist = nearbyUsers.users.any { it.name == state.user.name }
                if (!isAlreadyExist) {
                    nearbyUsers.users.add(state.user)
                }
                updateHomeState("Found nearby tapper")
            }
        }
    }

    fun sendAudio(payload: Payload) {
        commsManager.sendPayLoad(connectedUserId, payload)
    }

    private fun handlePayload(state: PayloadState) {
        when (state) {
            is OnPayloadFailure -> {
                _viewState.value = OnConnected(null)
            }

            is OnPayloadReceived -> {
                _viewState.value = OnConnected(state.payload)
            }

            is OnPayloadTransferUpdate -> {}
        }
    }

    private fun handleSuccess(userId: String) {
        connectedUserId = userId
        _viewState.value = OnConnected(null)
    }

    fun endConnection() {
        commsManager.endConnection()
    }
}