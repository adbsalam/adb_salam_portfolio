package uk.adbsalam.portfolio.settings.feature

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import uk.adbsalam.portfolio.utils.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
    isDynamic: Boolean,
    theme: Theme,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    onDismiss: () -> Unit
) {

    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            SettingsScreen(
                isDynamic = isDynamic,
                theme = theme,
                onDynamicColor = onDynamicColor,
                onTheme = onTheme,
                onDismiss = {
                    openDialog.value = false
                    onDismiss()
                }
            )

        }
    }
}

@Composable
@Preview
fun SettingsDialogPreview() {
    SettingsDialog(
        isDynamic = false,
        theme = Theme.SYSTEM,
        onDynamicColor = { /** unused **/ },
        onTheme = { /** unused **/ },
        onDismiss = { /** unused **/ }
    )
}