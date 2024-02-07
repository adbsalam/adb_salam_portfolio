package uk.adbsalam.portfolio.communication

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

open class CommunicationsComponentActivity: ComponentActivity() {

    fun handlePermissions(
        onPermissionsGranted: () -> Unit,
        onPermissionsError: () -> Unit,
        onRequirePermissions: () -> Unit
    ) {
        val permissionCaller =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perms ->
                if (hasMissingPermissions()) onPermissionsError() else onPermissionsGranted()
            }
        if (hasMissingPermissions()) {
            onRequirePermissions()
            permissionCaller.launch(permissionsList)
        } else {
            onPermissionsGranted()
        }
    }

    private fun hasMissingPermissions(): Boolean {
        return permissionsList.any {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
    }

}