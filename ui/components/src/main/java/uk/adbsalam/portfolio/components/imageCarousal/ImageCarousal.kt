package uk.adbsalam.portfolio.components.imageCarousal

import android.view.LayoutInflater.from
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager.ALIGNMENT_START
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import uk.adbsalam.portfolio.components.R.id.carousel_recycler_view
import uk.adbsalam.portfolio.components.R.layout.image_carousal_recycler

@Composable
fun ImageCarousal(
    modifier: Modifier = Modifier,
    @DrawableRes imageResList: List<Int>,
    isHero: Boolean = false
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        factory = { context ->
            val view = from(context).inflate(image_carousal_recycler, null, false)
            val recycler = view.findViewById<RecyclerView>(carousel_recycler_view)
            val recyclerAdapter = CustomAdapter(imageResList)
            recycler.adapter = recyclerAdapter

            if (isHero) {
                val heroLayout = CarouselLayoutManager(HeroCarouselStrategy())
                heroLayout.carouselAlignment = ALIGNMENT_START
                recycler.layoutManager = heroLayout

                val disableFlingSnapHelper: SnapHelper = CarouselSnapHelper()
                disableFlingSnapHelper.attachToRecyclerView(recycler)
            } else {
                recycler.layoutManager = CarouselLayoutManager()
            }

            view
        },
        update = {}
    )
}