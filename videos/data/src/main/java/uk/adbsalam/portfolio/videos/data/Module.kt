package uk.adbsalam.portfolio.videos.data

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
internal interface Module {

    @Binds
    @Singleton
    fun bindHomeRepository(videosRepository: VideosRepository): VideosRepo

    companion object {
        @Provides
        @Singleton
        fun provideHomeService(@PortfolioRetrofit retrofit: Retrofit): VideoService =
            retrofit.create(VideoService::class.java)
    }
}