package uk.adbsalam.portfolio.network

fun genericException(cause: Throwable) = Exception(
    "Sorry, something went wrong, please try again later",
    cause,
)
