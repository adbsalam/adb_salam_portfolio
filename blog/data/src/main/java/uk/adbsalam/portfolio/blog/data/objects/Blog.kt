package uk.adbsalam.portfolio.blog.data.objects

import uk.adbsalam.portfolio.blog.data.VComponents


data class Blog(
    val title: String,
    val subTitle: String,
    val components: List<Component>
) {
    data class Component(
        val type: VComponents,
        val data: String
    )

    companion object {
        fun mockBlog() = Blog(
            title = "How to automate your Paparazzi snapshot testing using @SnapIt",
            subTitle = "In this Blog I will show you how you can use SnapIt library to automate your snapshot generation",
            components = listOf(
                Component(
                    type = VComponents.HEADING,
                    data = "About SnapIt"
                ),
                blogText,
                kotlinCodeBlock
            )
        )

        val kotlinCodeBlock = Component(
            type = VComponents.CODE_BLOCK,
            data = "annotation class SnapIt(\n" +
                    "    val name: String = \"this is the test for screen\", // custom test name to use \n" +
                    "    val isScreen: Boolean = false, // is screen component - will provide system UI\n" +
                    "    val preview: Boolean = false, // require preview context\n" +
                    "    val isDark: Boolean = false, // is Dark Mode\n" +
                    "    val gif: Boolean = false, // capture a gif\n" +
                    "    val start: Long = 0L, // start duration in Long\n" +
                    "    val end: Long = 500L, // end duration in Long\n" +
                    "    val fps: Int = 30 // fps to use as Int\n" +
                    ")\n"
        )

        val blogText = Component(
            type = VComponents.TEXT,
            data = "Snapit is a powerful tool designed to automate the generation of Paparazzi tests by simple adding `@Snapit` Annotation on `@Preview` Functions, significantly reducing the time and resources required for creating snapshot tests. It leverages the `@Preview` Composables feature from the codebase, making it easy to implement and utilize. With `Snapit`, you can streamline your testing process and ensure the quality and reliability of your code."
        )
    }
}
