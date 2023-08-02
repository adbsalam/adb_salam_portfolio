package uk.adbsalam.portfolio.info.feature

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.formatter.DecimalFormatValueFormatter
import com.patrykandpatrick.vico.core.formatter.ValueFormatter
import uk.adbsalam.portfolio.info.feature.components.infocards.AndroidMainCard
import uk.adbsalam.portfolio.info.feature.components.infocards.SkillsInsightCard
import uk.adbsalam.portfolio.theming.Adb_Theme
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

        SkillsInsightCard()

        Spacer(modifier = Modifier.height(10.dp))

        val data = listOf(
            "2022-07-01" to 2f,
            "2022-07-02" to 6f,
            "2022-07-04" to 4f
        ).associate { (dateString, yValue) ->
            LocalDate.parse(dateString) to yValue
        }

        val chartEntryModelProducer = ChartEntryModelProducer(
            listOf(
                FloatEntry(0f, y = 50f),
                FloatEntry(1f, y = 40f),
                FloatEntry(2f, y = 60f),
                FloatEntry(3f, y = 90f),
            )
        )

        val xValuesToDates = data.keys.associateBy { it.toEpochDay().toFloat() }
        val chartEntryModel =
            xValuesToDates.keys.zip(data.values) { x, y -> entryOf(x, y) }.let { entryModelOf(it) }
        val horizontalAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            (xValuesToDates[value]
                ?: LocalDate.ofEpochDay(value.toLong())).format(DateTimeFormatter.ofPattern("dd MMM"))
        }

        Chart(
            chart = lineChart(),
            model = chartEntryModel
        )

        Chart(
            chart = lineChart(),
            chartModelProducer = chartEntryModelProducer,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(
                valueFormatter = horizontalAxisValueFormatter
            ),
        )

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