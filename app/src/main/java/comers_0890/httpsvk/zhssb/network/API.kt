package comers_0890.httpsvk.zhssb.network

import comers_0890.httpsvk.zhssb.data.models.RegistrationQueryBody
import comers_0890.httpsvk.zhssb.data.models.RegistrationResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @Headers("Authorization: Token f789cfe9d20da1f80c71080365dec445a2dcc2b1")
    @GET("queues/queue/")
    fun getQueueu(): Observable<String>

    @POST("users/register/")
    fun getRegistration(@Body body: RegistrationQueryBody): Observable<RegistrationResponse>
}