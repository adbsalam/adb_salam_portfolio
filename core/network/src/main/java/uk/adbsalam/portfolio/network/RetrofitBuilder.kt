package uk.adbsalam.portfolio.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Annotation class to Generate Retrofit for Portfolio API
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PortfolioRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl =
        "https://01dqwp1rv5.execute-api.eu-west-2.amazonaws.com/default/"

    private val logger =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    /**
     * OkHttp general interceptor to set network call properties
     */
    private fun provideOkHttpClientBuilder() = OkHttpClient
        .Builder()
        .addInterceptor(logger)
        .addInterceptor(HeaderInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    /**
     * @param moshi moshi instance to add converter factory
     */
    private fun buildRetrofit(
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClientBuilder())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi
        .Builder()
        .build()

    @Provides
    @Singleton
    @PortfolioRetrofit
    fun provideProfileRetrofit(moshi: Moshi): Retrofit = buildRetrofit(moshi)

}
