package uk.adbsalam.portfolio.walkie.feature.connect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.communication.NearbyUsers

@Composable
fun UserItem(user: NearbyUsers.User, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Text(text = user.name, modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
    }
}