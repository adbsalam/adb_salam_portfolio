package uk.adbsalam.portfolio.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Header interceptor to set API Key to all calls
 *
 * This is allow all calls modified at request level so we wont have to
 * add api key to each call seperatly.
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("x-api-key", BuildConfig.API_KEY)
                    .build(),
            )
        }
}
