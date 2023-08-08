package uk.adbsalam.portfolio.home.feature.components.card

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param tags tags list to show chips for
 * @param title title text to be set
 * @param body body or description of card component
 * @param readMore read more state as boolean
 * @param action action to perform on View button is clicked
 */
@Composable
internal fun CardInfoText(
    tags: List<String>,
    title: String,
    body: String,
    readMore: MutableState<Boolean>,
    action: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {

            tags.forEach { tag ->
                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            text = tag,
                            fontSize = 10.sp
                        )
                    },
                )
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExpandableText(
                readMore = readMore,
                text = body
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = action,
                content = { Text(text = "View") }
            )

            TextButton(
                content = { Text(text = "Read more") },
                onClick = { readMore.value = !readMore.value }
            )
        }
    }
}

@PreviewLight
@Composable
@SnapIt(name = "CardInfoText - Light Mode")
internal fun CardInfoTextPreviewLight() {
    Adb_Theme {
        val readMore = remember { mutableStateOf(false) }
        CardInfoText(
            tags = HomeScreenItem.createMock().first().tags,
            title = "Sample Title",
            body = "This is body example",
            readMore = readMore,
            action = { /*unused*/ }
        )
    }
}

@PreviewDark
@Composable
@SnapIt(name = "CardInfoText - Dark Mode", isDark = true)
internal fun CardInfoTextPreviewDark() {
    Adb_Theme(isSystemDark = true) {
        val readMore = remember { mutableStateOf(false) }
        CardInfoText(
            tags = HomeScreenItem.createMock().first().tags,
            title = "Sample Title",
            body = "This is body example",
            readMore = readMore,
            action = { /*unused*/ }
        )
    }
}

