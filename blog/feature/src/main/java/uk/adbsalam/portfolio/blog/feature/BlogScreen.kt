package uk.adbsalam.portfolio.blog.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.adbsalam.portfolio.blog.data.objects.Blog
import uk.adbsalam.portfolio.blog.feature.blogGenerator.BlogGenerator
import uk.adbsalam.portfolio.blog.feature.components.BlogAuthorHeader
import uk.adbsalam.portfolio.blog.feature.components.BlogSubTitle
import uk.adbsalam.portfolio.blog.feature.components.BlogTitle
import uk.adbsalam.portfolio.components.LoadingLotti
import uk.adbsalam.portfolio.navigation.BlogScreenArgs
import uk.adbsalam.portfolio.navigation.navArgs
import uk.adbsalam.portfolio.samples.feature.InProgressPage
import uk.adbsalam.portfolio.theming.PreviewDark
import uk.adbsalam.portfolio.theming.PreviewLight
import uk.adbsalam.portfolio.theming.adbRoundedBackground
import uk.adbsalam.portfolio.theming.appbackground.Adb_Screen_Theme
import uk.adbsalam.portfolio.utils.Theme


@Composable
fun BlogScreen(
    navController: NavController,
    viewModel: BlogViewModel = hiltViewModel()
) {
    val args = navController.navArgs<BlogScreenArgs>()
    LaunchedEffect(key1 = null) { viewModel.getBlog(args?.query) }
    val uiState by viewModel.viewState.collectAsState()
    BlogScreen(
        uiState = uiState,
        currentTheme = viewModel.getCurrentTheme()
    )
}

@Composable
private fun BlogScreen(
    uiState: BlogState,
    currentTheme: Theme
) {
    when (uiState) {
        BlogState.OnError -> InProgressPage()

        BlogState.OnLoading -> LoadingLotti(
            modifier = Modifier.fillMaxSize(),
            msg = "Loading"
        )

        is BlogState.OnSuccess -> Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .statusBarsPadding()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    BlogTitle(title = uiState.blog.title)
                    BlogSubTitle(text = uiState.blog.subTitle)
                    Spacer(modifier = Modifier.height(6.dp))
                    BlogAuthorHeader()
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 20.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .adbRoundedBackground()
                        .padding(
                            horizontal = 10.dp
                        )
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    BlogGenerator(uiState.blog.components, currentTheme)
                    Spacer(modifier = Modifier.height(300.dp))
                }
            }
        }
    }
}

@PreviewLight
@Composable
private fun BlogScreenLightPreview() {
    Adb_Screen_Theme {
        BlogScreen(
            uiState = BlogState.OnSuccess(Blog.mockBlog()),
            currentTheme = Theme.SYSTEM
        )
    }
}


@PreviewDark
@Composable
private fun BlogScreenDarkPreview() {
    Adb_Screen_Theme(isDark = true) {
        BlogScreen(
            uiState = BlogState.OnSuccess(Blog.mockBlog()),
            currentTheme = Theme.SYSTEM
        )
    }
}