package uk.adbsalam.portfolio.reviews.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uk.adbsalam.portfolio.network.PortfolioRetrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ReviewsModule {

    @Binds
    @Singleton
    fun bindHomeRepository(reviewsRepository: ReviewsRepository): ReviewsRepo

    companion object {
        @Provides
        @Singleton
        fun provideHomeService(@PortfolioRetrofit retrofit: Retrofit): ReviewsService =
            retrofit.create(ReviewsService::class.java)
    }
}