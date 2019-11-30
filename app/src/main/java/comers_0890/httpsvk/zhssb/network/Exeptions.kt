package comers_0890.httpsvk.zhssb.network

import comers_0890.httpsvk.zhssb.data.ResponseError.ResponseError

data class ApiException(val responseError: ResponseError?) : Exception()

class NoDataException() : Exception()

class NoSelectedParamsException() : Exception()

class TimeoutException(val t: Throwable) : Exception()

class NetworkException(val t: Throwable) : Exception()

class SessionExpiredException(val responseError: ResponseError?) : Exception()

class UnknownException(val t: Throwable) : Exception()