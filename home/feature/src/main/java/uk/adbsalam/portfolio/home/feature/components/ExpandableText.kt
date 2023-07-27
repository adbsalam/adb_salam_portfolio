package uk.adbsalam.portfolio.home.feature.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ExpandableText(
    readMore: MutableState<Boolean>,
    text: String
) {
    Column(modifier = Modifier
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