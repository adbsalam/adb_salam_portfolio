package uk.adbsalam.portfolio.blog.data

import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.blog.data.objects.BlogResponse
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.network.genericException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BlogRepository @Inject constructor(
    private val blogService: BlogService
) : BlogRepo {

    /**
     * Make network call to collect blog components
     * @return Success response if call success
     * Else return General Exception since theres no error body needed
     */
    override suspend fun blogComponents(query: String): Response<Blog> {
        return try {
            val blogItems = blogService.blogItems(item = query).toBlog()

            Response.Success(data = blogItems)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(genericException(e))
        }
    }


    private fun BlogResponse.toBlog() = Blog(
        title = title,
        subTitle = subTitle,
        components = components.toBlogComponents()
    )

    private fun List<BlogResponse.Component>.toBlogComponents() = this.map { component ->
        Blog.Component(
            type = VComponents.values().firstOrNull { it.name == component.type }
                ?: VComponents.UNKNOWN,
            data = component.data
        )
    }
}