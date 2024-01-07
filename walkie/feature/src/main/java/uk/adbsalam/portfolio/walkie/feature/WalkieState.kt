package uk.adbsalam.portfolio.walkie.feature

sealed class WalkieState() {
    object OnLoading : WalkieState()
    object OnRequirePermissions : WalkieState()
    object OnPermissionsError : WalkieState()
    object OnConnect : WalkieState()
}
