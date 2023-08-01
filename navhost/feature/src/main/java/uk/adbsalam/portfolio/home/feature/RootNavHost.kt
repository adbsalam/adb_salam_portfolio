package uk.adbsalam.portfolio.home.feature

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.intellij.lang.annotations.Language
import uk.adbsalam.portfolio.navigation.NavigationScreen
import uk.adbsalam.portfolio.navigation.composeRoute
import uk.adbsalam.portfolio.navigation.route
import uk.adbsalam.portfolio.theming.primary_dark
import uk.adbsalam.portfolio.theming.primary_light
import uk.adbsalam.portfolio.utils.Theme

@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
""".trimIndent()


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreenNavHost(
    theme: Theme,
    onTheme: (Theme) -> Unit,
    onDynamicColor: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    val gradientColorOne =
        if (theme == Theme.DARK || isSystemInDarkTheme()) primary_dark else Color(0xFF94BBB7)

    val gradientColorTwo =
        if (theme == Theme.DARK || isSystemInDarkTheme()) primary_light else Color(0xFFF7E2A5)

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
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.OnHome.route(),
        ) {

            composeRoute(NavigationScreen.OnHome) {
                NavBarScreen(
                    onTheme = onTheme,
                    onDynamicColor = onDynamicColor
                )
            }
        }
    }
}