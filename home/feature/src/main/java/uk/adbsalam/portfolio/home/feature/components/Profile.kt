package uk.adbsalam.portfolio.home.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.compose_color
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Hard coded component since will not change anytime soon
 */
@Composable
internal fun Profile(
    theme: Theme,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val isSystemLight = theme == Theme.SYSTEM && !isSystemInDarkTheme()
        val image =
            if (theme == Theme.LIGHT || isSystemLight) R.drawable.ic_garden_light else R.drawable.ic_garden_dark
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = image),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(id = R.drawable.ic_dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .background(
                        color = Color.DarkGray.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Abdul Salam",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "@adb_salam",
                    color = compose_color,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
@PreviewLight
@SnapIt
internal fun ProfilePreview() {
    Profile(Theme.LIGHT, modifier = Modifier.size(500.dp))
}
