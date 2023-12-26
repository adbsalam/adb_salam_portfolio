package uk.adbsalam.portfolio.blog.feature.blogTextFormatter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString


fun getTextWithCodeFormatting(text: String) = buildAnnotatedString {
    append(text)
    FormatType.values().forEach {
        applyFormat(text, it.pattern, it.color, it.excludeFirst)
    }

    KeyWords.values().forEach {
        val keywordPattern = "\\b${it.keyword}\\b"
        applyFormat(text, keywordPattern, it.color, false)
    }
}

private fun AnnotatedString.Builder.applyFormat(
    text: String,
    format: String,
    color: Color,
    excludeFirst: Boolean
) {
    val regex = Regex(format)
    val matches = regex.findAll(text)
    matches.forEach {
        val range = it.range
        addStyle(
            style = SpanStyle(color = color),
            start = if (excludeFirst) range.first + 1 else range.first,
            end = range.last + 1
        )
    }
}

enum class FormatType(val pattern: String, val color: Color, val excludeFirst: Boolean) {
    VALUE(pattern = "=\\s*([^\"]\\S*)", color = Color.Cyan, excludeFirst = true),
    TYPE_DEFINATION(pattern = ":\\s*(\\w+)", color = Color.Cyan, true),
    COMMENT(pattern = "//(.+?)\\n", color = Color.Gray, false),
    TEXT_VALUE(pattern = """"(.+?)"""", color = Color.Green, false)
}

enum class KeyWords(val keyword: String, val color: Color = Color(0xFFFFA500)) {
    FUN("fun"),
    IMPORT("import"),
    CLASS("class"),
    VAR("var"),
    VAL("val"),
    ANNOTATION("annotation")
}
