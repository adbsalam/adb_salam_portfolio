package uk.adbsalam.portfolio.settings.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.components.RadioGroup
import uk.adbsalam.portfolio.utils.Theme

@Composable
internal fun SettingsScreen(
    isDynamic: Boolean,
    theme: Theme,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
    onDismiss: () -> Unit
) {

    val preSelectedDynamic = remember { mutableStateOf(isDynamic) }
    val preSelectedTheme = remember { mutableStateOf(theme) }

    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(30.dp),
                color = MaterialTheme.colorScheme.background
            )
            .padding(20.dp, vertical = 20.dp)
    ) {


        Text(
            text = "Settings",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )

        Text(
            text = "Use Dynamic Colors",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        RadioGroup(
            items = listOf(true, false),
            title = { item -> if (item) "Yes" else "No" },
            preSelect = isDynamic,
            onSelected = { item ->
                preSelectedDynamic.value = item
                onDynamicColor(item)
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Use Dynamic Colors",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        RadioGroup(
            items = Theme.values().toList(),
            title = { item -> item.title },
            preSelect = theme,
            onSelected = { item ->
                preSelectedTheme.value = item
                onTheme(item)
            }
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        TextButton(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.End),
            onClick = { onDismiss() }
        ) {
            Text(text = "Ok")
        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(
        isDynamic = false,
        theme = Theme.SYSTEM,
        onDynamicColor = { /** unused **/ },
        onTheme = { /** unused **/ },
        onDismiss = { /** unused **/ }
    )
}