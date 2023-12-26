package uk.adbsalam.portfolio.blog.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.adbsalam.portfolio.blog.data.BlogRepo
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val blogRepo: BlogRepo,
    private val themePrefs: AppSharedPrefManager.ThemePrefs
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<BlogState>(BlogState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    suspend fun getBlog(query: String?) {
        if (query == null) {
            _viewState.value = BlogState.OnError
        } else {
            try {
                when (val result = blogRepo.blogComponents(query)) {
                    is Response.Failure -> {
                        _viewState.value = BlogState.OnError
                    }

                    is Response.Success -> {
                        _viewState.value = BlogState.OnSuccess(result.data)

                    }
                }
            } catch (e: Exception) {
                _viewState.value = BlogState.OnError
            }
        }
    }

    fun getCurrentTheme() = themePrefs.theme()
}
