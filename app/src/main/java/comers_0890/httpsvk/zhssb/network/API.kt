package comers_0890.httpsvk.zhssb.network

import comers_0890.httpsvk.zhssb.data.models.LoginRequest
import comers_0890.httpsvk.zhssb.data.models.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @GET("users/auth")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>
}