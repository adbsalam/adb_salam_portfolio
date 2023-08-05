package uk.adbsalam.portfolio.info.feature.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ChevronRight
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.info.data.WorkHistory
import uk.adbsalam.portfolio.info.feature.util.workIcon

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkInfo(
    showDivider: Boolean,
    workHistory: WorkHistory,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {

        var expand by remember { mutableStateOf(false) }
        var chevronRotation by remember { mutableStateOf(0f) }

        val chevronRotate by animateFloatAsState(
            targetValue = chevronRotation,
            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 8.dp, end = 10.dp)
                .clickable {
                    chevronRotation = if (!expand) 90f else 0f
                    expand = !expand
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.WorkHistory,
                    contentDescription = null,
                    tint = Color(workHistory.color)
                )

                Text(
                    text = workHistory.company,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = workHistory.duration,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier.rotate(chevronRotate)
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
                                    painter = painterResource(id = workIcon(it.icon)),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            },
                            onClick = {}
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = workHistory.description)

                Spacer(modifier = Modifier.height(20.dp))
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
        workHistory = WorkHistory.createMock().first(),
    )
}