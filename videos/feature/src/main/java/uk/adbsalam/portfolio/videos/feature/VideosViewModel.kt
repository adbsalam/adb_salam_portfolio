package uk.adbsalam.portfolio.videos.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.network.Response
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.VideosRepo
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val themePrefs: AppSharedPrefManager.ThemePrefs,
    private val videosRepo: VideosRepo
) : ViewModel() {

    private val _viewState = MutableStateFlow<VideosState>(VideosState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * Load videos an init videos screen
     */
    internal suspend fun loadVideos() {
        fetchVideos()
    }

    internal fun fetchVideos() {
        _viewState.value = VideosState.OnLoading

        viewModelScope.launch {

            when (val result = videosRepo.videos()) {
                is Response.Failure -> {
                    _viewState.value =
                        VideosState.OnError("something went wrong, please try again")
                }

                is Response.Success -> {
                    _viewState.value = VideosState.OnVideos(result.data)
                }
            }
        }
    }

    /**
     * get current theme app us using from prefs
     */
    internal fun currentTheme(): Theme {
        return themePrefs.theme()
    }
}