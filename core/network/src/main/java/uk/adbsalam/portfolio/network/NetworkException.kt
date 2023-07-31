package uk.adbsalam.portfolio.network

/**
 * @param cause Exception to be printed,
 *
 * This is general exception to return with a message
 */
fun genericException(cause: Throwable) = Exception(
    "Sorry, something went wrong, please try again later",
    cause,
)
