package uk.adbsalam.portfolio.network

/**
 * Response Class to catch network results
 */
sealed class Response<T> {
    class Success<T>(val data: T) : Response<T>()
    class Failure<T>(val exception: Exception) : Response<T>()
}
