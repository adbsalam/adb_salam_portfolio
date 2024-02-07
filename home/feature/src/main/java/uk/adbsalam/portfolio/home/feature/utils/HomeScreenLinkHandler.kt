package uk.adbsalam.portfolio.home.feature.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import uk.adbsalam.portfolio.data.SocialMedia
import uk.adbsalam.portfolio.navigation.deeplinkGesture
import uk.adbsalam.portfolio.navigation.deeplinkWalkie
import uk.adbsalam.portfolio.navigation.deeplinkYoutube

internal fun handleDeepLinkForItem(
    deeplink: String,
    context: Context,
    navigateDeeplink: (String) -> Unit
) {
    when (deeplink) {
        deeplinkYoutube -> {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/channel/UCct4uE53LK-r_0DlNBM_InA")
            )
            ContextCompat.startActivity(context, intent, null)
        }

        deeplinkGesture -> {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/MuhammadAbdulSalam/arduino_gesture")
            )
            ContextCompat.startActivity(context, intent, null)
        }

        deeplinkWalkie -> {
            val activityToStart =
                "uk.adbsalam.portfolio.walkie.feature.WalkieActivity"
            try {
                val c = Class.forName(activityToStart)
                val intent = Intent(context, c)
                ContextCompat.startActivity(context, intent, null)
            } catch (ignored: ClassNotFoundException) {
                ignored.printStackTrace()
            }
        }

        else -> {
            navigateDeeplink(deeplink)
        }
    }
}

internal fun startActivityForLink(context: Context, media: SocialMedia) {
    when (media) {
        SocialMedia.G_PLAY -> {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "Share play store link")
            i.putExtra(Intent.EXTRA_TEXT, media.link)
            ContextCompat.startActivity(context, Intent.createChooser(i, "Share via"), null)
        }

        SocialMedia.GMAIL -> {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(media.link))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Query for Salam")
            ContextCompat.startActivity(context, Intent.createChooser(intent, "Email via..."), null)
        }

        else -> {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(media.link))
            ContextCompat.startActivity(context, intent, null)
        }
    }
}