package uk.adbsalam.portfolio.home.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.data.SocialMedia
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param mediaType Social Media type to return Icons
 * @return Icon Resource ID for item image
 */
@DrawableRes
fun iconRes(mediaType: SocialMedia): Int {
    return when (mediaType) {
        SocialMedia.LINKED_IN -> R.drawable.ic_linked_in
        SocialMedia.GIT -> R.drawable.ic_git
        SocialMedia.YOUTUBE -> R.drawable.ic_youtube
        SocialMedia.FACEBOOK -> R.drawable.ic_fb
        SocialMedia.INSTA -> R.drawable.ic_insta
        SocialMedia.GMAIL -> R.drawable.ic_gmail
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun SocialMediaCarousal() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Find me on social media",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 12.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp)
        ) {
            SocialMedia.values().forEach { media ->
                item {
                    Box(modifier = Modifier.padding(end = 15.dp)) {
                        Image(
                            painter = painterResource(id = iconRes(media)),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(12.dp)
                                .size(50.dp)
                        )
                    }
                }
            }
        }
    }
}