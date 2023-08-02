package uk.adbsalam.portfolio.info.feature.components.infocards

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.info.feature.components.InfoTitle
import uk.adbsalam.portfolio.info.feature.components.charts.CircularChart
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@Composable
internal fun AndroidMainCard(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .statusBarsPadding()
            .adbRoundedBackground()
            .padding(14.dp),
    ) {

        InfoTitle(title = "Credit Insight")

        Row(modifier = Modifier.fillMaxWidth()) {
            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Credit",
                subTitle = "Kotlin",
                keyOne = "Credit Used : £60.00 ",
                keyTwo = "Credit Available: £40.00",
                icon = R.drawable.ic_credit,
                percent = 0.6f,
            )

            CircularChart(
                modifier = Modifier.weight(1f),
                title = "Total Orders",
                subTitle = "Compose",
                keyOne = "Card: £40.00",
                keyTwo = "Credit: £60.00",
                icon = R.drawable.ic_shop,
                percent = 0.4f,
            )
        }
    }
}

@Preview
@Composable
internal fun AndroidMainCardPreviewLight(){
    Adb_Theme {
        AndroidMainCard()
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
internal fun AndroidMainCardPreviewDark(){
    Adb_Theme(true) {
        AndroidMainCard()
    }
}