const val COMPILE_SDK = 34
const val MIN_SDK = 24
const val COMPOSE_COMPILER = "1.4.7"
const val DEBUG_FLAVOR = "debug"

fun asNameSpace(name: String): String{
    return "uk.adbsalam.portfolio.${name}"
}

fun asSnapName(name: String): String{
    val path = name.replace(".", "/")
    return "src/test/java/uk/adbsalam/portfolio/$path"
}
