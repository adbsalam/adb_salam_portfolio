package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        Text(
            text = "Check out your orders insights",
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
                            lineBackgroundShader = null
                        )
                    )
                ),
                model = chartEntryModel,
                startAxis = startAxis(),
                bottomAxis = bottomAxis(
                    valueFormatter = horizontalAxisValueFormatter
                )
            )
        }

        SkillsInsightCard()


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