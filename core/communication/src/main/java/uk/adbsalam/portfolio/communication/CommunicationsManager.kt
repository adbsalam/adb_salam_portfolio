package uk.adbsalam.portfolio.communication

import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.PayloadTransferUpdate
import com.google.android.gms.nearby.connection.Strategy
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionFailure
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionInitiated
import uk.adbsalam.portfolio.communication.ConnectionState.OnConnectionSuccess
import uk.adbsalam.portfolio.communication.ConnectionState.OnDisconnected
import uk.adbsalam.portfolio.communication.ConnectionState.OnEndpointFound
import uk.adbsalam.portfolio.communication.ConnectionState.OnLog
import uk.adbsalam.portfolio.communication.NearbyUsers.User
import javax.inject.Inject

class CommunicationsManager @Inject constructor() {

    private val strategy = Strategy.P2P_STAR
    private lateinit var mConnectionsClient: ConnectionsClient
    private val SERVICE_ID = "uk.adbsalam.portfolio.debug.SERVICE_ID"

    fun initCommunications(
        connectionClient: ConnectionsClient,
        username: String,
        onStateUpdate: (ConnectionState) -> Unit
    ) {
        mConnectionsClient = connectionClient
        startDiscovering(onStateUpdate)
        startAdvertising(username, onStateUpdate)
    }

    private fun startAdvertising(username: String, onStateUpdate: (ConnectionState) -> Unit) {
        val advertisingOptions = AdvertisingOptions.Builder()
        advertisingOptions.setStrategy(strategy)
        mConnectionsClient
            .startAdvertising(
                username,
                SERVICE_ID,
                connectionCallback(onStateUpdate),
                advertisingOptions.build()
            )
            .addOnSuccessListener { onStateUpdate(OnLog("Advertising started successfully")) }
            .addOnFailureListener { e -> e.printStackTrace() }
    }

    private fun startDiscovering(onStateUpdate: (ConnectionState) -> Unit) {
        val discoveryOptions = DiscoveryOptions.Builder()
        discoveryOptions.setStrategy(strategy)
        mConnectionsClient
            .startDiscovery(
                SERVICE_ID,
                discoveryCallback(onStateUpdate),
                discoveryOptions.build()
            )
            .addOnSuccessListener { onStateUpdate(OnLog("Discovery started successfully")) }
            .addOnFailureListener { e -> e.printStackTrace() }
    }

    private fun discoveryCallback(onStateUpdate: (ConnectionState) -> Unit) =
        object : EndpointDiscoveryCallback() {
            override fun onEndpointFound(endpointId: String, info: DiscoveredEndpointInfo) {
                onStateUpdate(OnLog("Found tapper nearby"))
                if (SERVICE_ID == info.serviceId) {
                    onStateUpdate(OnEndpointFound(User(endpointId, info.endpointName)))
                }
            }

            override fun onEndpointLost(endpointId: String) {
                onStateUpdate(OnLog("onEndpointLost $endpointId"))
            }
        }

    fun connectToEndpoint(user: User, onStateUpdate: (ConnectionState) -> Unit) {
        mConnectionsClient
            .requestConnection(user.name, user.id, connectionCallback(onStateUpdate))
            .addOnSuccessListener { onStateUpdate(OnLog(log = "Connection Started Success")) }
            .addOnFailureListener { onStateUpdate(OnLog(log = "Connection Started Failed")) }
    }

    fun acceptConnection(id: String, onStateUpdate: (PayloadState) -> Unit) {
        mConnectionsClient
            .acceptConnection(id, mPayloadCallback(onStateUpdate))
            .addOnFailureListener { e -> onStateUpdate(PayloadState.OnPayloadFailure(e.message.toString())) }
    }

    private fun connectionCallback(onStateUpdate: (ConnectionState) -> Unit) =
        object : ConnectionLifecycleCallback() {
            override fun onConnectionInitiated(endpointId: String, info: ConnectionInfo) {
                onStateUpdate(OnConnectionInitiated(endpointId, info))
            }

            override fun onConnectionResult(endpointId: String, resolution: ConnectionResolution) {
                if (resolution.status.isSuccess) onStateUpdate(OnConnectionSuccess(endpointId))
                else onStateUpdate(OnConnectionFailure)
            }

            override fun onDisconnected(p0: String) {
                onStateUpdate(OnDisconnected(p0))
            }
        }

    private fun mPayloadCallback(onStateUpdate: (PayloadState) -> Unit) =
        object : PayloadCallback() {
            override fun onPayloadReceived(endpointId: String, payload: Payload) {
                onStateUpdate(PayloadState.OnPayloadReceived(endpointId, payload))
            }

            override fun onPayloadTransferUpdate(
                endpointId: String,
                update: PayloadTransferUpdate
            ) {
                onStateUpdate(PayloadState.OnPayloadTransferUpdate(endpointId, update))
            }
        }

    fun sendPayLoad(userId: String, payload: Payload) {
        mConnectionsClient.sendPayload(userId, payload)
            .addOnFailureListener { e -> }
    }

    fun endConnection() {
        mConnectionsClient.stopDiscovery()
        mConnectionsClient.stopAdvertising()
        mConnectionsClient.stopAllEndpoints()
    }
}