package uk.adbsalam.portfolio.info.feature.components.timeline

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.info.feature.WorkHistory

@Composable
internal fun TimeLineCard(
    workHistory: WorkHistory,
    modifier: Modifier
) {

    var expand by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = workHistory.company,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = workHistory.duration,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if(workHistory.logo == 0) return@Row

                Image(
                    painter = painterResource(id = workHistory.logo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(80.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            AnimatedVisibility(visible = expand) {
                Column() {
                    Text(text = workHistory.description)
                }
            }

            TextButton(onClick = { expand = !expand }) {
                Text(text = "Expand")
            }

        }
    }
}

@Composable
@Preview
fun TimeLineCardNBrownPreview() {
    TimeLineCard(
        workHistory = WorkHistory.createMock().first(),
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
@Preview
fun TimeLineCardSagossPreview() {
    TimeLineCard(
        workHistory = WorkHistory.createMock()[1],
        modifier = Modifier.fillMaxWidth()
    )
}