package uk.adbsalam.portfolio.info.feature

import uk.adbsalam.portfolio.info.data.objects.Infographics
import uk.adbsalam.portfolio.info.data.objects.WorkHistory

internal sealed interface InfoScreenState {
    object OnLoading : InfoScreenState
    data class OnError(val msg: String) : InfoScreenState
    data class OnInfo(
        val infographics: Infographics,
        val workHistory: WorkHistory
    ) : InfoScreenState
}