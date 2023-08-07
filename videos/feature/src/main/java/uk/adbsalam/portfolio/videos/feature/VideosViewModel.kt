package uk.adbsalam.portfolio.videos.feature

import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.adbsalam.portfolio.prefs.AppSharedPrefManager
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.portfolio.videos.data.VideoData
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val themePrefs: AppSharedPrefManager.ThemePrefs
) : ViewModel() {

    private val _viewState =
        MutableStateFlow<VideosState>(VideosState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    suspend fun loadVideos() {
        _viewState.value = VideosState.OnVideos(
            videos = VideoData.createMock()
        )
    }

    fun currentTheme(): Theme {
        return themePrefs.theme()
    }

}