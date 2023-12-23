package uk.adbsalam.portfolio.blog.data

enum class VComponents {
    TEXT, IMAGE, FAQ, BUTTON, CODE_BLOCK, HEADING, BULLET_POINT, UNKNOWN;

    companion object {
        fun fromInt(type: String) = values().associateBy(VComponents::name)[type] ?: UNKNOWN
    }
}