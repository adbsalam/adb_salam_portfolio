package uk.adbsalam.portfolio.home.feature.components

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.createChooser
import android.net.Uri.parse
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.data.SocialMedia
import uk.adbsalam.portfolio.data.SocialMedia.GMAIL
import uk.adbsalam.portfolio.data.SocialMedia.G_PLAY
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.snapit.annotations.SnapIt


/**
 * @param mediaType Social Media type to return Icons
 * @return Icon Resource ID for item image
 */
@DrawableRes
fun iconRes(mediaType: SocialMedia): Int {
    return when (mediaType) {
        SocialMedia.G_PLAY -> R.drawable.g_play
        SocialMedia.LINKED_IN -> R.drawable.ic_linked_in
        SocialMedia.GIT -> R.drawable.ic_git
        SocialMedia.YOUTUBE -> R.drawable.ic_youtube
        SocialMedia.FACEBOOK -> R.drawable.ic_fb
        SocialMedia.INSTA -> R.drawable.ic_insta
        GMAIL -> R.drawable.ic_gmail
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun SocialMediaCarousal() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val context = LocalContext.current

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
                                .clickable { startActivityForLink(context, media) }
                        )
                    }
                }
            }
        }
    }
}

private fun startActivityForLink(context: Context, media: SocialMedia) {
    when (media) {
        G_PLAY -> {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(EXTRA_SUBJECT, "Share play store link")
            i.putExtra(Intent.EXTRA_TEXT, media.link)
            startActivity(context, createChooser(i, "Share via"), null)
        }

        GMAIL -> {
            val intent = Intent(ACTION_SENDTO)
            intent.data = parse("mailto:")
            intent.putExtra(EXTRA_EMAIL, arrayOf(media.link))
            intent.putExtra(EXTRA_SUBJECT, "Query for Salam")
            startActivity(context, createChooser(intent, "Email via..."), null)
        }

        else -> {
            val intent = Intent(ACTION_VIEW, parse(media.link))
            startActivity(context, intent, null)
        }
    }
}