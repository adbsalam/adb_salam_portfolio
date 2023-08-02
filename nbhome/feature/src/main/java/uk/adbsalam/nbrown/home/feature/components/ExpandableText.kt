package uk.adbsalam.nbrown.home.feature.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

/**
 * @param readMore read more state boolean to expand when clicked
 * @param text text to show
 */
@Composable
internal fun ExpandableText(
    readMore: MutableState<Boolean>,
    text: String
) {
    Column(modifier = Modifier
        .wrapContentSize()
        .animateContentSize(animationSpec = tween(500))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { readMore.value = !readMore.value }) {

        if (readMore.value) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(widthDp = 200)
@Composable
internal fun ExpandableTextCollapsedPreview(){
    val readMore = remember { mutableStateOf(false) }
    ExpandableText(
        readMore = readMore,
        text = "This is some long text for 2 lines and should be ellipsed to shorter lines since it cannot fit in two lines"
    )
}

@Preview(widthDp = 200)
@Composable
internal fun ExpandableTextExpandedPreview(){
    val readMore = remember { mutableStateOf(true) }
    ExpandableText(
        readMore = readMore,
        text = "This is some long text for 2 lines and should be ellipsed to shorter lines since it cannot fit in two lines"
    )
}
