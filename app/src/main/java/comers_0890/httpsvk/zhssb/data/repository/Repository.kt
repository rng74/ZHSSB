package comers_0890.httpsvk.zhssb.data.repository

import androidx.lifecycle.LiveData
import comers_0890.httpsvk.zhssb.data.ResponseError.Resource
import comers_0890.httpsvk.zhssb.data.models.RegistrationQueryBody
import comers_0890.httpsvk.zhssb.data.models.RegistrationResponse


interface Repository {
//    fun authLogin(body: AuthorizationBody): LiveData<Resource<AuthorizationResponse>>

    fun getQueue(): LiveData<Resource<String>>

    fun getRegistration(body: RegistrationQueryBody): LiveData<Resource<RegistrationResponse>>
}