package uk.adbsalam.portfolio.blog.feature.blogGenerator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.blog.data.VComponents
import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.blog.feature.components.BlogBulletPoint
import uk.adbsalam.portfolio.blog.feature.components.BlogButton
import uk.adbsalam.portfolio.blog.feature.components.BlogCodeBlock
import uk.adbsalam.portfolio.blog.feature.components.BlogHeading
import uk.adbsalam.portfolio.blog.feature.components.BlogImage
import uk.adbsalam.portfolio.blog.feature.components.BlogText
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun BlogGenerator(
    components: List<Blog.Component>,
    currentTheme: Theme
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        components.forEach {
            when (it.type) {
                VComponents.TEXT -> BlogText(text = it.data)
                VComponents.IMAGE -> BlogImage(name = it.data)
                VComponents.FAQ -> {}
                VComponents.BUTTON -> BlogButton(action = it.data)
                VComponents.CODE_BLOCK -> BlogCodeBlock(text = it.data, currentTheme)
                VComponents.HEADING -> BlogHeading(text = it.data)
                VComponents.BULLET_POINT -> BlogBulletPoint(text = it.data)
                VComponents.UNKNOWN -> {}
            }
        }
    }

}