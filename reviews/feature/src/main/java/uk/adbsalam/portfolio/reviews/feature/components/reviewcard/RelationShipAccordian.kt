package uk.adbsalam.portfolio.reviews.feature.components.reviewcard

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.reviews.data.ReviewsData
import uk.adbsalam.portfolio.theming.Adb_Theme

@Composable
fun RelationShipAccordian(
    review: ReviewsData
) {
    var expand by remember { mutableStateOf(false) }
    var chevronRotation by remember { mutableStateOf(0f) }

    val chevronRotate by animateFloatAsState(
        targetValue = chevronRotation,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                chevronRotation = if (!expand) 90f else 0f
                expand = !expand
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Handshake,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Relationship",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier.rotate(chevronRotate)
            )
        }

        AnimatedVisibility(
            visible = expand,
            enter = fadeIn(tween(500)) + expandVertically(tween(500)),
            exit = fadeOut(tween(500)) + shrinkVertically(tween(500)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = review.relation,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = review.relationDetails,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun InfoGraphicsPreviewLight() {
    Adb_Theme {
        RelationShipAccordian(review = ReviewsData.createMock().first())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoGraphicsPreviewDark() {
    Adb_Theme(true) {
        RelationShipAccordian(review = ReviewsData.createMock().first())
    }
}