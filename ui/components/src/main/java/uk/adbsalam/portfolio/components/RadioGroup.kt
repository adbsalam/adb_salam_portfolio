package uk.adbsalam.portfolio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.snapit.annotations.SnapIt


@Composable
fun <T> RadioGroup(
    items: List<T>,
    title: (T) -> String,
    preSelect: T,
    onSelected: (T) -> Unit
) {
    val selected = remember { mutableStateOf(preSelect) }
    Column {
        items.forEach { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (item == selected.value),
                        onClick = {
                            selected.value = item
                            onSelected(item)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (item == selected.value),
                    onClick = {
                        selected.value = item
                        onSelected(item)
                    }
                )
                Text(
                    text = title(item),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
@SnapIt(name = "RadioGroup - first item selected")
internal fun RadioGroupPreview() {
    RadioGroup(
        items = listOf("itemOne", "itemTwo"),
        title = { item -> item },
        preSelect = "itemOne",
        onSelected = { /** unused **/}
    )
}
