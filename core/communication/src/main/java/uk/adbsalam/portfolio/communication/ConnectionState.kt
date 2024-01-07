package uk.adbsalam.portfolio.communication

import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadTransferUpdate

sealed class ConnectionState {
    data class OnLog(val log: String) : ConnectionState()
    data class OnEndpointFound(val user: NearbyUsers.User) : ConnectionState()
    data class OnEndpointLost(val endpointId: String) : ConnectionState()

    data class OnConnectionInitiated(val endpointId: String, val info: ConnectionInfo) : ConnectionState()
    data class OnDisconnected(val p0: String) : ConnectionState()
    data class OnConnectionSuccess(val connectedUserId: String) : ConnectionState()
    object OnConnectionFailure : ConnectionState()
}

sealed class PayloadState{
    data class OnPayloadFailure(val log: String): PayloadState()
    data class OnPayloadReceived(val endPoint: String, val payload: Payload): PayloadState()
    data class OnPayloadTransferUpdate(val endPoint: String, val payloadTransferUpdate: PayloadTransferUpdate): PayloadState()
}
