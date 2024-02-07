package uk.adbsalam.portfolio.walkie.feature.connect

import com.google.android.gms.nearby.connection.Payload
import uk.adbsalam.portfolio.communication.NearbyUsers

sealed class ConnectState {
    object OnLoading : ConnectState()
    data class OnUpdateLogs(val log: String, val nearbyUsers: NearbyUsers) : ConnectState()
    data class OnConnected(val payload: Payload?) : ConnectState()
}