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
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("x-api-key", "18G0K0zq7paLmYTueZiK74YbEzrrALjO5R8rRHKp")
                    .build()
            )
        }
    }
}