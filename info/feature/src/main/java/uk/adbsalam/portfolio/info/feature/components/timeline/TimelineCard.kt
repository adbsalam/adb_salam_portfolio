package uk.adbsalam.portfolio.info.feature.components.timeline

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.feature.WorkHistory
import uk.adbsalam.portfolio.theming.Adb_Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun TimeLineCard(
    workHistory: WorkHistory,
    modifier: Modifier
) {
    var expand by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.WorkHistory, contentDescription = null)
                Text(
                    text = workHistory.company,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = workHistory.duration,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                workHistory.tags.forEach {
                    AssistChip(
                        label = { Text(text = it.tag) },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.sizeIn(maxHeight = 30.dp, maxWidth = 30.dp),
                                painter = painterResource(id = it.icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        onClick = {}
                    )
                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(visible = expand) {
                Text(text = workHistory.description)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { expand = !expand },
                content = { Text(text = if (expand) "see less" else "see more") }
            )
        }
    }
}

@Composable
@Preview
fun TimeLineCardNBrownPreview() {
    Adb_Theme() {
        TimeLineCard(
            workHistory = WorkHistory.createMock().first(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,)
fun TimeLineCardSagossPreview() {
    Adb_Theme(true) {
        TimeLineCard(
            workHistory = WorkHistory.createMock()[1],
            modifier = Modifier.fillMaxWidth()
        )
    }
}

