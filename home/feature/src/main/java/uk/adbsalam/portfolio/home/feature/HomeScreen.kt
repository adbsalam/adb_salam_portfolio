package uk.adbsalam.portfolio.home.feature

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.launch
import uk.adbsalam.portfolio.components.SettingsIcon
import uk.adbsalam.portfolio.components.shimmerCarousal.ShimmerCardCarousal
import uk.adbsalam.portfolio.home.feature.components.Profile
import uk.adbsalam.portfolio.home.feature.components.SocialMediaCarousal
import uk.adbsalam.portfolio.home.feature.components.card.InfoCard
import uk.adbsalam.portfolio.home.feature.components.card.LottiInfoCard
import uk.adbsalam.portfolio.home.feature.utils.HomeItemType
import uk.adbsalam.portfolio.home.feature.utils.HomeScreenItem
import uk.adbsalam.portfolio.home.feature.utils.getDrawableRes
import uk.adbsalam.portfolio.home.feature.utils.getRawRes
import uk.adbsalam.portfolio.navigation.deeplinkGallery
import uk.adbsalam.portfolio.navigation.deeplinkGesture
import uk.adbsalam.portfolio.navigation.deeplinkPatrolla
import uk.adbsalam.portfolio.navigation.deeplinkSnapit
import uk.adbsalam.portfolio.navigation.deeplinkYoutube
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
    navigateDeeplink: (String) -> Unit,
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {
    val settings = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val headerHeight = LocalConfiguration.current.screenHeightDp / 3
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
            Row(
                modifier = Modifier.padding(start = 14.dp, end = 14.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Component Lab",
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(imageVector = Icons.Default.Info, contentDescription =  null)
            }

            ShimmerCardCarousal()

            items.forEach { item ->
                InfoCardByType(
                    item = item,
                    animateLottie = !scrollState.isScrollInProgress
                ) {
                    when (item.deeplink) {
                        deeplinkPatrolla,
                        deeplinkSnapit,
                        deeplinkGallery -> {
                            navigateDeeplink(item.deeplink)
                            return@InfoCardByType
                        }
                    }

                    val url = when (item.deeplink) {
                        deeplinkYoutube -> "https://www.youtube.com/channel/UCct4uE53LK-r_0DlNBM_InA"
                        deeplinkGesture -> "https://github.com/MuhammadAbdulSalam/arduino_gesture"
                        else -> ""
                    }

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(context, intent, null)
                }
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

    if (scrollState.value > 0) {
        BackHandler {
            scope.launch {
                scrollState.animateScrollTo(0)
            }
        }
    }
}

@PreviewLight
@Composable
@SnapIt
internal fun HomeLightPreview() {
    Adb_Screen_Theme {
        HomeScreen(
            items = HomeScreenItem.createMock(),
            navigateDeeplink = { /* unused */ },
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
            navigateDeeplink = { /* unused */ },
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
private fun InfoCardByType(
    item: HomeScreenItem,
    animateLottie: Boolean,
    action: () -> Unit
) {
    when (item.type) {
        HomeItemType.IMAGE_CARD ->
            InfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getDrawableRes(item.res),
                action = action
            )

        HomeItemType.LOTTI_CARD -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getRawRes(item.res),
                animate = animateLottie,
                action = action
            )
        }

        HomeItemType.LOTTI_SINGLE -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                resId = getRawRes(item.res),
                animate = animateLottie,
                action = action
            )
        }
    }
}
