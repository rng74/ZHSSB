package comers_0890.httpsvk.zhssb.data.repository

import android.util.Log
import com.google.gson.Gson
import comers_0890.httpsvk.zhssb.data.ResponseError.Resource
import comers_0890.httpsvk.zhssb.data.ResponseError.ResponseError
import comers_0890.httpsvk.zhssb.network.*
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRepositoryImpl(val gson: Gson) {
    protected fun getError(e: Throwable) =
        Resource.error(msg = null, data = null, exception = handleError(e))

    private fun handleError(e: Throwable): Exception = when (e) {
        is HttpException -> {
            try {
                val response = e.response()

                if (response != null) {
                    val responseError: ResponseError? =
                        gson.fromJson(response.errorBody()?.string(), ResponseError::class.java)
                    if (response.code() == 401) {
                        SessionExpiredException(responseError)
                    } else {
                        ApiException(responseError)
                    }
                } else {
                    Log.d("ERROR", e.toString())
                    UnknownException(e)
                }
            } catch (e1: Exception) {
                UnknownException(e)
            }
        }
        is SocketTimeoutException -> TimeoutException(e)
        is IOException -> NetworkException(e)
        else -> UnknownException(e)
    }
}