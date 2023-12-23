package uk.adbsalam.portfolio.blog.feature

import uk.adbsalam.portfolio.blog.data.objects.Blog

sealed class BlogState {
    object OnLoading : BlogState()
    data class OnSuccess(val blog: Blog) : BlogState()
    object OnError : BlogState()
}