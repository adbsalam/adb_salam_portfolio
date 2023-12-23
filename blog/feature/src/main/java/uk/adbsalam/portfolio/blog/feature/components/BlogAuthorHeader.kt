package uk.adbsalam.portfolio.blog.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.PreviewLight

/**
 * Hard coded component since will not change anytime soon
 */
@Composable
fun BlogAuthorHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.ic_dp),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Author",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "AbdulSalam",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
@PreviewLight
internal fun BlogAuthorHeaderPreview() {
    BlogAuthorHeader()
}