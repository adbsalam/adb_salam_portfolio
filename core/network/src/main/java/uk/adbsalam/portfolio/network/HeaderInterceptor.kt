package uk.adbsalam.portfolio.network

import okhttp3.Interceptor
import okhttp3.Response

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