package uk.adbsalam.portfolio.blog.feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.blog.feature.blogTextFormatter.createCodeBlockRect
import uk.adbsalam.portfolio.blog.feature.blogTextFormatter.getCodeBlockIndexes

@Composable
fun BlogText(text: String) {
    Box {
        val codeBlocksLocations = getCodeBlockIndexes(text)
        var selectedPartPaths by remember { mutableStateOf(listOf<Path>()) }
        Text(
            text.removeCodeBlockRegex(),
            style = MaterialTheme.typography.bodyMedium,
            onTextLayout = { layoutResult ->
                selectedPartPaths =
                    codeBlocksLocations.map { Path().createCodeBlockRect(it, layoutResult) }
            },
            modifier = Modifier.drawBehind {
                selectedPartPaths.forEach { path ->
                    drawPath(path, style = Fill, color = Color.Blue.copy(alpha = 0.2f))
                    drawPath(path, style = Stroke(width = 2f), color = Color.Blue)
                }
            }
        )
    }
}

private fun String.removeCodeBlockRegex() = this.replace("`", "")

@Composable
@Preview
fun BlogTextPreview() {
    BlogText(text = Blog.blogText.data)
}
