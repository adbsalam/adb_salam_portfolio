package uk.adbsalam.portfolio.blog.feature.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.blog.feature.blogTextFormatter.getTextWithCodeFormatting
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.roundedContainerShape
import uk.adbsalam.portfolio.utils.Theme


@Composable
fun BlogCodeBlock(
    text: String,
    currentTheme: Theme
) {
    val context = LocalContext.current
    val codeText = getTextWithCodeFormatting(text)
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = getBlockBackgroundColor(currentTheme),
                shape = roundedContainerShape
            )
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.CopyAll,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 4.dp)
                .clickable { textCopyThenPost(codeText.text, context) }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = codeText,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}

@Composable
private fun getBlockBackgroundColor(currentTheme: Theme): Color {
    return when {
        currentTheme == Theme.SYSTEM && isSystemInDarkTheme() -> Color.Black
        currentTheme == Theme.SYSTEM && !isSystemInDarkTheme() -> MaterialTheme.colorScheme.primary
        currentTheme == Theme.LIGHT -> MaterialTheme.colorScheme.primary
        currentTheme == Theme.DARK -> Color.Black
        currentTheme == Theme.CHRISTMAS -> Color.Black
        else -> Color.Black
    }
}

fun textCopyThenPost(textCopied: String, context: Context) {
    val clipboardManager = getSystemService(context, ClipboardManager::class.java)
    clipboardManager?.setPrimaryClip(ClipData.newPlainText("", textCopied))
    // Only show a toast for Android 12 and lower.
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
}

@PreviewLight
@Composable
fun CodeBlockKotlinLightPreview() {
    Adb_Theme {
        BlogCodeBlock(
            Blog.kotlinCodeBlock.data,
            currentTheme = Theme.SYSTEM
        )
    }
}

@PreviewDark
@Composable
fun CodeBlockKotlinDarkPreview() {
    Adb_Theme(isSystemDark = true) {
        BlogCodeBlock(
            Blog.kotlinCodeBlock.data,
            currentTheme = Theme.SYSTEM
        )
    }
}
