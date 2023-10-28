package uk.adbsalam.portfolio.components.shimmerCarousal

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShimmerViewModel @Inject constructor(): ViewModel() {

    private val _shimmerState = MutableStateFlow(LazyRowShimmerState())
    internal val shimmerState = _shimmerState.asStateFlow()

    fun updateState(
        shimmerState: LazyRowShimmerState.LazyRowItemShimmer
    ){
        _shimmerState.value.stateList.add(shimmerState)
    }
}