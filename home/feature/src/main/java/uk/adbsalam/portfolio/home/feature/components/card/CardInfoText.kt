package uk.adbsalam.portfolio.home.feature.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.snapit.annotations.SnapIt

@Composable
internal fun CardInfoText(
    imageHint: String,
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
        Text(
            text = imageHint,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
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

@Preview
@Composable
@SnapIt(name = "CardInfoText - not expanded")
internal fun CardInfoTextPreview() {
    val readMore = remember { mutableStateOf(false) }
    CardInfoText(
        imageHint = "Android",
        title = "Sample Title",
        body = "This is body example",
        readMore = readMore,
        action = { /*unused*/ }
    )
}