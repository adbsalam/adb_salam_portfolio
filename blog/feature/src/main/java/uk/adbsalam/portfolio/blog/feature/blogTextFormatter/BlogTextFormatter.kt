package uk.adbsalam.portfolio.blog.feature.blogTextFormatter

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextLayoutResult


val codeBlockRegex = Regex("`(.*?)`")

data class CodeBlockRange(
    val start: Int,
    val stop: Int
)

fun getCodeBlockIndexes(text: String): List<CodeBlockRange> {
    val codeBlocks = codeBlockRegex.findAll(text)
    return codeBlocks.mapIndexed { index, block ->
        CodeBlockRange(
            start = (block.range.first - (index * 2)),
            stop = block.range.last - ((index * 2) + 1)
        )
    }.toList()
}

fun Path.createCodeBlockRect(codeBlockRange: CodeBlockRange, layoutResult: TextLayoutResult): Path {
    val cornerRadius = CornerRadius(x = 10f, y = 10f)
    return this.apply {
        val boundingBoxes = layoutResult
            .getBoundingBoxesForRange(
                start = codeBlockRange.start,
                end = codeBlockRange.stop
            )
        for (i in boundingBoxes.indices) {
            val boundingBox = boundingBoxes[i]
            val leftCornerRoundRect =
                if (i == 0) cornerRadius else CornerRadius.Zero
            val rightCornerRoundRect =
                if (i == boundingBoxes.indices.last) cornerRadius else CornerRadius.Zero
            addRoundRect(
                RoundRect(
                    boundingBox.inflate(horizontalDelta = 3f, verticalDelta = -2f),
                    topLeft = leftCornerRoundRect,
                    topRight = rightCornerRoundRect,
                    bottomRight = rightCornerRoundRect,
                    bottomLeft = leftCornerRoundRect,
                )
            )
        }
    }
}


fun Rect.inflate(verticalDelta: Float, horizontalDelta: Float) =
    Rect(
        left = left - horizontalDelta,
        top = top - verticalDelta,
        right = right + horizontalDelta,
        bottom = bottom + verticalDelta,
    )

fun TextLayoutResult.getBoundingBoxesForRange(start: Int, end: Int): List<Rect> {
    var prevRect: Rect? = null
    var firstLineCharRect: Rect? = null
    val boundingBoxes = mutableListOf<Rect>()
    for (i in start..end) {
        val rect = getBoundingBox(i)
        val isLastRect = i == end

        if (isLastRect && firstLineCharRect == null) {
            firstLineCharRect = rect
            prevRect = rect
        }

        if (!isLastRect && rect.right == 0f) continue

        if (firstLineCharRect == null) {
            firstLineCharRect = rect
        } else if (prevRect != null) {
            if (prevRect.bottom != rect.bottom || isLastRect) {
                boundingBoxes.add(
                    firstLineCharRect.copy(right = prevRect.right)
                )
                firstLineCharRect = rect
            }
        }
        prevRect = rect
    }
    return boundingBoxes
}