package uk.adbsalam.portfolio.info.feature.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @param title of the card ti show
 *
 * This returns a text component with title and divider
 */
@Composable
internal fun InfoTitle(
    title: String
){
    Column() {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 20.dp)
        )
    }
}

@Preview
@Composable
internal fun InfoTitlePreview(){
    InfoTitle("Android Test")
}