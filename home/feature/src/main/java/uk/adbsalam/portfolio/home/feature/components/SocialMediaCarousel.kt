package uk.adbsalam.portfolio.home.feature.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.data.SocialMedia


@Preview
@Composable
internal fun SocialMediaCarousal() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Find me on social media",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp)
        ) {
            SocialMedia.values().forEach { media ->
                item {
                    Box(modifier = Modifier.padding(end = 15.dp)) {
                        SocialMediaItem(
                            resId = iconRes(media)
                        )
                    }
                }
            }
        }
    }
}

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

@Composable
internal fun SocialMediaItem(
    @DrawableRes resId: Int
) {
    Card(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {

            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(50.dp)
            )

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
@Preview
fun SocialMediaCardPreview() {
    SocialMediaItem(
        resId = R.drawable.ic_git
    )
}