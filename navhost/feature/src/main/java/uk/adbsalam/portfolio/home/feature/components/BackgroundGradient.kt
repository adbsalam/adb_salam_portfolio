package uk.adbsalam.portfolio.home.feature.components

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import uk.adbsalam.portfolio.theming.primary_dark
import uk.adbsalam.portfolio.theming.primary_light
import uk.adbsalam.portfolio.utils.Theme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GradientColumn(
    theme: Theme,
    content: @Composable () -> Unit
) {
    val gradientColorOne =
        if (theme == Theme.DARK || isSystemInDarkTheme()) primary_dark else Color(0xFFBED3D1)

    val gradientColorTwo =
        if (theme == Theme.DARK || isSystemInDarkTheme()) primary_light else Color(0xFFF5EBCE)

    Column(modifier = Modifier
        .fillMaxSize()
        .drawWithCache {
            val shader = RuntimeShader(CUSTOM_SHADER)
            val shaderBrush = ShaderBrush(shader)
            shader.setFloatUniform("resolution", size.width, size.height)
            onDrawBehind {
                shader.setColorUniform(
                    "color",
                    android.graphics.Color.valueOf(
                        gradientColorTwo.red,
                        gradientColorTwo.green,
                        gradientColorTwo.blue,
                        gradientColorTwo.alpha
                    )
                )
                shader.setColorUniform(
                    "color2",
                    android.graphics.Color.valueOf(
                        gradientColorOne.red,
                        gradientColorOne.green,
                        gradientColorOne.blue,
                        gradientColorOne.alpha
                    )
                )
                drawRect(shaderBrush)
            }
        }
    ) {
        content()
    }
}