package uk.adbsalam.portfolio.info.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.info.data.InfoRepo
import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory
import uk.adbsalam.portfolio.info.feature.InfoScreenState.OnError
import uk.adbsalam.portfolio.network.Response
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val infoRepo: InfoRepo
) : ViewModel() {

    private val _viewState = MutableStateFlow<InfoScreenState>(InfoScreenState.OnLoading)
    internal val viewState = _viewState.asStateFlow()

    /**
     * Init info details
     * This will change state from loading to OnInfo
     */
    internal suspend fun initInfo() {
        fetchInfoAndWork()
    }

    internal fun fetchInfoAndWork() {
        viewModelScope.launch {
            val infographics: Infographics
            val workHistory: WorkHistory

            when (val result = infoRepo.infographics()) {
                is Response.Failure -> {
                    _viewState.value = OnError("something went wrong, please try again")
                    return@launch
                }

                is Response.Success -> {
                    infographics = result.data
                }
            }

            when (val result = infoRepo.workHistory()) {
                is Response.Failure -> {
                    _viewState.value = OnError("something went wrong, please try again")
                    return@launch
                }

                is Response.Success -> {
                    workHistory = result.data
                }
            }

            _viewState.value = InfoScreenState.OnInfo(
                infographics = infographics,
                workHistory = workHistory
            )
        }
    }
}