package uk.adbsalam.portfolio.info.feature.components.timeline

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkInfo(
    showDivider: Boolean,
    workHistory: WorkHistory
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {

        var expand by remember { mutableStateOf(false) }

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 8.dp)
                .clickable {
                    expand = !expand
                }
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

        AnimatedVisibility(visible = expand) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
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
            }
        }

        if (!showDivider) return@Column

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )

    }
}

@Preview
@Composable
fun WorkInfoPreview() {
    WorkInfo(
        showDivider = true,
        workHistory = WorkHistory.createMock().first()
    )
}