package uk.adbsalam.portfolio.samples.feature.tabLayout.editor

internal data class TabIndicatorAnimationState(
    val offsetAnimationDampingRatio: Int,
    val offsetAnimationStiffness: Int,
    val widthAnimationDampingRatio: Int,
    val widthAnimationStiffness: Int
) {
    companion object {
        val empty = TabIndicatorAnimationState(1, 1, 1, 1)
    }
}