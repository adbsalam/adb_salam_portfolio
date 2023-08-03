package uk.adbsalam.portfolio.info.feature.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import uk.adbsalam.portfolio.theming.adbRoundedBackground

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun CreditSlider() {

    var creditValue by remember { mutableStateOf(0) }
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .adbRoundedBackground()
            .padding(12.dp)
    ) {

        InfoTitle(title = "Increase credit limit")

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "£",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            AnimatedContent(
                targetState = creditValue,
                transitionSpec = {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeIn(initialAlpha = 0f, animationSpec = tween(500))with
                            slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.Up,
                                animationSpec = tween(durationMillis = 500)
                            ) + fadeOut(targetAlpha = 1f, animationSpec = tween(500))
                },
                contentAlignment = Alignment.Center
            ) { targetCount ->
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                Text(
                    modifier = Modifier.width(100.dp),
                    text = "${(targetCount * 10) + 100}.00",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Slider(
            modifier = Modifier,
            value = creditValue.toFloat(),
            valueRange = 0f..10f,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary
            ),
            onValueChange = {
                creditValue = it.toInt()
            }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Apply Now")
        }
    }

}