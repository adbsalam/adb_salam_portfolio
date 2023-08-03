package uk.adbsalam.portfolio.info.feature.components

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.adbsalam.portfolio.components.R
import uk.adbsalam.portfolio.theming.Adb_Theme
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MakePayment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .adbRoundedBackground()
    ) {

        var expand by remember { mutableStateOf(false) }

        AnimatedContent(
            targetState = expand,
            transitionSpec = {

                fadeIn(
                    animationSpec = tween(600, easing = EaseIn)
                ).with(
                    fadeOut(
                        animationSpec = tween(600, easing = EaseOut)
                    )
                ).using(
                    SizeTransform(
                        clip = false,
                        sizeAnimationSpec = { initialSize, targetSize ->
                            tween(600, easing = EaseInOut)
                        }
                    )
                )

            }
        ) { targetState ->
            if (!targetState) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .adbRoundedBackground()
                        .padding(16.dp)
                ) {
                    InfoTitle(title = "Make a payment options")

                    Text(
                        text = "You have a minimum payment of £5.00 left to pay, would you like to make a payment now?",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { expand = !expand },
                        content = { Text(text = "Make a payment") }
                    )

                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .adbRoundedBackground()
                        .padding(16.dp)
                ) {
                    InfoTitle(title = "Please fill in details")

                    Text(
                        text = "Payment request fo minimum amount £5.00. Please select how would you like to pay?",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth()) {
                        InputChip(
                            selected = false,
                            onClick = {},
                            label = { Text(text = "GPay") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_google),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        InputChip(
                            selected = false,
                            onClick = {},
                            label = { Text(text = "Card") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_card),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = true,
                            onClick = { },
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Pay with card",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = false,
                            onClick = { },
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Pay with G-Pay",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { },
                            content = { Text(text = "Pay Now") }
                        )
                        TextButton(onClick = { expand = !expand }) {
                            Text(text = "Cancel")
                        }

                    }

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MakePaymentLightPreview() {
    Adb_Theme {
        MakePayment()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MakePaymentDarkPreview() {
    Adb_Theme(true) {
        MakePayment()
    }
}