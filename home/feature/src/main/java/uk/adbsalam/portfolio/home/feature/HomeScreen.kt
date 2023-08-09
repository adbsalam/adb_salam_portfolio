package uk.adbsalam.portfolio.home.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.SettingsIcon
import uk.adbsalam.portfolio.home.feature.components.Profile
import uk.adbsalam.portfolio.home.feature.components.SocialMediaCarousal
import uk.adbsalam.portfolio.home.feature.components.card.InfoCard
import uk.adbsalam.portfolio.home.feature.components.card.LottiInfoCard
import uk.adbsalam.portfolio.home.feature.utils.HomeItemType
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.home.feature.utils.getDrawableRes
import uk.adbsalam.portfolio.home.feature.utils.getRawRes
import uk.adbsalam.portfolio.settings.feature.SettingsDialog
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme
import uk.adbsalam.snapit.annotations.SnapIt

/**
 * @param items list of items to show on home screen
 * @param onDynamicColor action on dynamic color value change
 * @param onTheme action on theme value change
 */
@Composable
internal fun HomeScreen(
    items: List<HomeScreenItem>,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {
    val settings = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val headerHeight = LocalConfiguration.current.screenHeightDp / 3

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight.dp)
                .statusBarsPadding()
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 3.5f)
                    translationY = 0.8f * scrollState.value
                },
        ) {
            Profile(modifier = Modifier.align(Alignment.Center))
            SettingsIcon(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = { settings.value = true }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {

            items.forEach {
                InfoCardByType(item = it)
            }

            SocialMediaCarousal()
        }
    }

    if (settings.value) {
        SettingsDialog(
            onDynamicColor = onDynamicColor,
            onTheme = onTheme,
            onDismiss = { settings.value = false }
        )
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun HomeLightPreview() {
    Adb_Screen_Theme {
        HomeScreen(
            items = HomeScreenItem.createMock(),
            onDynamicColor = { /* unused */ },
            onTheme = { /* unused */ }
        )
    }
}

@PreviewDark
@Composable
@SnapIt(isDark = true)
internal fun HomeDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        HomeScreen(
            items = HomeScreenItem.createMock(),
            onDynamicColor = { /* unused */ },
            onTheme = { /* unused */ }
        )
    }
}

/**
 * @param item current item to be used
 *
 * @return Card by Item type to be shown
 */
@Composable
private fun InfoCardByType(item: HomeScreenItem) {
    when (item.type) {
        HomeItemType.IMAGE_CARD ->
            InfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getDrawableRes(item.res),
                action = {}
            )

        HomeItemType.LOTTI_CARD -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getRawRes(item.res),
                action = {}
            )
        }

        HomeItemType.LOTTI_SINGLE -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getRawRes(item.res),
                action = {}
            )
        }
    }
}
