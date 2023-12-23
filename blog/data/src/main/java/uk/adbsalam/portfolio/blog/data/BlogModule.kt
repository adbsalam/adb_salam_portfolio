package uk.adbsalam.portfolio.blog.data

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
internal interface BlogModule {

    @Binds
    @Singleton
    fun bindHomeRepository(blogRepo: BlogRepository): BlogRepo

    companion object {
        @Provides
        @Singleton
        fun provideHomeService(@PortfolioRetrofit retrofit: Retrofit): BlogService =
            retrofit.create(BlogService::class.java)
    }
}