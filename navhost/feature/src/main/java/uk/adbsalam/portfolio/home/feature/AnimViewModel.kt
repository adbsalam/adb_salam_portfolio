package uk.adbsalam.portfolio.home.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AnimViewModel @Inject constructor(): ViewModel() {

    var _animOne = MutableStateFlow(0f)
    internal val animOne = _animOne.asStateFlow()

}