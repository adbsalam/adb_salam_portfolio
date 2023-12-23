package uk.adbsalam.portfolio.blog.data

import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.network.Response

/**
 * BlogRepo Repo interface to expose to required modules
 */
interface BlogRepo {
    suspend fun blogComponents(query: String): Response<Blog>
}