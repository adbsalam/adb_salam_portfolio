package uk.adbsalam.portfolio.data

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
internal interface HomeModule {

    @Binds
    @Singleton
    fun bindWishlistRepo(nBrownWishlistRepo: HomeRepository): HomeRepo

    companion object {
        @Provides
        @Singleton
        fun provideHomeService(@PortfolioRetrofit retrofit: Retrofit): HomeService =
            retrofit.create(HomeService::class.java)
    }
}