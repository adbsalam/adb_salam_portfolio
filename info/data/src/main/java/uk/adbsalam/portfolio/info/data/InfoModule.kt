package uk.adbsalam.portfolio.info.data

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
internal interface InfoModule {

    @Binds
    @Singleton
    fun bindInfoRepo(infoRepository: InfoRepository): InfoRepo

    companion object {
        @Provides
        @Singleton
        fun provideInfoService(@PortfolioRetrofit retrofit: Retrofit): InfoService =
            retrofit.create(InfoService::class.java)
    }
}