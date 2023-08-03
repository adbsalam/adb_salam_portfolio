package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.style.LocalChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.info.feature.components.CreditSlider
import uk.adbsalam.portfolio.info.feature.components.InfoTitle
import uk.adbsalam.portfolio.info.feature.components.MakePayment
import uk.adbsalam.portfolio.info.feature.components.WorkInfo
import uk.adbsalam.portfolio.info.feature.components.infocards.AndroidMainCard
import uk.adbsalam.portfolio.info.feature.components.infocards.SkillsInsightCard
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoGraphics() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AndroidMainCard()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .adbRoundedBackground()
                .padding(all = 12.dp)
        ) {

            InfoTitle(title = "Promo code discounts")

            Row(
                Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.Bottom
            ) {

                Text(
                    text = "Total Promo Code Savings:",
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "£237.83",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_discount),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {}) {
                Text(text = "See available promos")
            }
        }

        MakePayment()

        CreditSlider()

        Text(
            text = "Check out your orders insights",
            style = MaterialTheme.typography.titleMedium
        )

        SkillsInsightCard()

        Text(
            text = "Annual spending stats",
            style = MaterialTheme.typography.titleMedium
        )

        val data = listOf(
            "2022-01-01" to 10f,
            "2022-02-02" to 30f,
            "2022-03-04" to 5f,
            "2022-04-05" to 10f,
            "2022-05-21" to 10f,
            "2022-06-02" to 30f,
            "2022-07-04" to 5f,
            "2022-08-05" to 10f,
        ).associate { (dateString, yValue) ->
            LocalDate.parse(dateString) to yValue
        }

        val xValuesToDates = data.keys.associateBy { it.monthValue.toFloat() }
        val chartEntryModel =
            xValuesToDates.keys.zip(data.values) { x, y -> entryOf(x, y) }.let { entryModelOf(it) }
        val horizontalAxisValueFormatter =
            AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
                (xValuesToDates[value]
                    ?: LocalDate.ofEpochDay(value.toLong())).format(DateTimeFormatter.ofPattern("MMM"))
            }


        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {


            ProvideChartStyle(
                chartStyle = LocalChartStyle.current.copy(
                    axis = LocalChartStyle.current.axis.copy(
                        axisLabelColor = MaterialTheme.colorScheme.onBackground,
                        axisLabelTextSize = 14.sp,
                        axisLineColor = MaterialTheme.colorScheme.onBackground,
                        axisGuidelineColor = Color.LightGray,
                    ),
                )
            ) {
                Chart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .adbRoundedBackground()
                        .padding(12.dp),
                    chart = lineChart(
                        lines = listOf(
                            lineSpec(
                                lineColor = MaterialTheme.colorScheme.onBackground,
                                lineBackgroundShader = null,
                            )
                        )
                    ),
                    model = chartEntryModel,
                    startAxis = startAxis(),
                    bottomAxis = bottomAxis(
                        valueFormatter = horizontalAxisValueFormatter,
                        guideline = null
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .adbRoundedBackground()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            val mock = WorkHistory.createMock()
            mock.forEachIndexed { index, item ->
                WorkInfo(
                    showDivider = index != mock.lastIndex,
                    workHistory = item,
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun InfoGraphicsPreviewLight() {
    Adb_Theme {
        InfoGraphics()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoGraphicsPreviewDark() {
    Adb_Theme(true) {
        InfoGraphics()
    }
}