package uk.adbsalam.portfolio.home.feature.components.card

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import uk.adbsalam.snapit.annotations.SnapIt

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
            Text(text = text)
        } else {
            Text(text = text, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview(widthDp = 200)
@Composable
@SnapIt(name = "ExpandableText - not expanded")
internal fun ExpandableTextCollapsedPreview(){
    val readMore = remember { mutableStateOf(false) }
    ExpandableText(
        readMore = readMore,
        text = "This is some long text for 2 lines and should be ellipsed to shorter lines since it cannot fit in two lines"
    )
}

@Preview(widthDp = 200)
@Composable
@SnapIt(name = "ExpandableText - expanded")
internal fun ExpandableTextExpandedPreview(){
    val readMore = remember { mutableStateOf(true) }
    ExpandableText(
        readMore = readMore,
        text = "This is some long text for 2 lines and should be ellipsed to shorter lines since it cannot fit in two lines"
    )
}
