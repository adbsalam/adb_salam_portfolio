package uk.adbsalam.portfolio.blog.feature

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val textAA =
            "Snapit is a powerful tool designed to automate the generation of Paparazzi tests by simple adding `@Snapit` Annotation on `@Preview` Functions, significantly reducing the time and resources required for creating snapshot tests. It leverages the <c>@Preview</c> Composables feature from the codebase, making it easy to implement and utilize. With <c>Snapit</c>, you can streamline your testing process and ensure the quality and reliability of your code."


        val pattern = Regex("`(.*?)`")
        val indexs = pattern.findAll(textAA)
        val codeBlocks = arrayListOf<CodeBlocks.HighlightIndex>()
        indexs.forEachIndexed { index, block ->
            codeBlocks.add(
                CodeBlocks.HighlightIndex(
                    start = (block.range.first - (3 * (index + 1))),
                    stop = block.range.last - (3 * (index + 1))
                )
            )
        }


//        codeBlocks.forEach{
//            println("-----------------------" + it.start)
//            println("-----------------------" + it.stop)
//            println("-----------------------XXXX--------------------")
//        }

    }
}

data class CodeBlocks(
    val locations: List<HighlightIndex>
) {
    data class HighlightIndex(
        val start: Int,
        val stop: Int
    )
}
