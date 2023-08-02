package uk.adbsalam.nbrown.home.feature

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.nbrown.home.feature.components.CarousalItem
import uk.adbsalam.nbrown.home.feature.components.ProductsCarousal
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.components.SettingsIcon
import uk.adbsalam.portfolio.settings.feature.SettingsDialog
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.utils.Theme

@Composable
fun NBrownHome(
    onDynamicColor: (Boolean) -> Unit,
    onTheme: (Theme) -> Unit,
) {

    val settings = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .statusBarsPadding()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_main_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .align(Alignment.Center)
            )

            SettingsIcon(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { settings.value = true }
            )
        }

        HomeImage()

        ProductsCarousal()


//        LottiInfoCard(
//            resId = R.raw.ic_stats,
//            tags = listOf("Track", "Spending", "Summary"),
//            title = "Spending Insight",
//            body = "Track you order summary and look at your spendings and much more such as bla bla and other things",
//            action = {}
//        )
    }


    if (settings.value) {
        SettingsDialog(
            onDynamicColor = onDynamicColor,
            onTheme = onTheme,
            onDismiss = { settings.value = false }
        )
    }

}


@Preview
@Composable
fun NBrownHomeLightPreview() {
    Adb_Theme {
        NBrownHome(
            onTheme = {},
            onDynamicColor = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NBrownHomeDarkPreview() {
    Adb_Theme(true) {
        NBrownHome(
            onTheme = {},
            onDynamicColor = {}
        )
    }
}