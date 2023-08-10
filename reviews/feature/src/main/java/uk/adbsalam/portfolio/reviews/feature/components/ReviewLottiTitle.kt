package uk.adbsalam.portfolio.reviews.feature.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * Page title for Reviews
 * Lotti file and components to show as page title
 */
@Composable
internal fun ReviewLottiTitle() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_reviews))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = Integer.MAX_VALUE,
            isPlaying = true
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
        )
        Text(
            text = "Reviews",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Here are some of reviews and recommendations given to me. These reviews are taken from my linked in profile and to read more reviews or know more about me, you can click following button to visit my LinkedIn Profile",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_linked_in),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "LinkedIn")
            },
            onClick = {
                val url = "https://www.linkedin.com/in/muhammad-abdulsalam-1253a7178/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(context, intent, null)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun ReviewLottiTitlePreviewLight() {
    Adb_Theme {
        ReviewLottiTitle()
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun ReviewLottiTitlePreviewDark() {
    Adb_Theme(true) {
        ReviewLottiTitle()
    }
}