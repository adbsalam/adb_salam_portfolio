package uk.adbsalam.portfolio.reviews.feature.components.reviewcard

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
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.reviews.data.objects.ReviewItems
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param review relationship accordion to show data with
 * This is expandable accordion to show and hide details of relationship
 */
@Composable
internal fun RelationShipAccordion(
    review: ReviewItems.Review
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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
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

@PreviewLight
@Composable
@SnapIt
internal fun RelationShipAccordionPreviewLight() {
    Adb_Theme {
        RelationShipAccordion(review = ReviewItems.createMock().reviews.first())
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun RelationShipAccordionPreviewDark() {
    Adb_Theme(isSystemDark = true) {
        RelationShipAccordion(review = ReviewItems.createMock().reviews.first())
    }
}