package comers_0890.httpsvk.zhssb.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import comers_0890.httpsvk.zhssb.data.ResponseError.Resource
import comers_0890.httpsvk.zhssb.data.models.RegistrationQueryBody
import comers_0890.httpsvk.zhssb.data.models.RegistrationResponse
import comers_0890.httpsvk.zhssb.data.preferences.Preferences
import comers_0890.httpsvk.zhssb.network.API
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class Repositorylmpl(val api: API, val preferences: Preferences, gson: Gson) : BaseRepositoryImpl(gson), Repository {
    override fun getRegistration(body: RegistrationQueryBody): LiveData<Resource<RegistrationResponse>> {
        val tempLiveData: MutableLiveData<Resource<RegistrationResponse>> = MutableLiveData()
        api.
            getRegistration(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tempLiveData.postValue(Resource.success(it))
            }, {
                tempLiveData.postValue(getError(it))
            })
        return tempLiveData
    }

    override fun getQueue(): LiveData<Resource<String>> {
        val tempLiveData: MutableLiveData<Resource<String>> = MutableLiveData()
        api.getQueueu()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tempLiveData.postValue(Resource.success(it))
            }, {
                tempLiveData.postValue(getError(it))
            })
        return tempLiveData
    }

//    override fun authLogin(body: AuthorizationBody): LiveData<Resource<AuthorizationResponse>> {
//        val tempLiveData: MutableLiveData<Resource<AuthorizationResponse>> = MutableLiveData()
//        api
//            .authLogin(body)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                preferences.auth("JWT " + it.token)
//                tempLiveData.postValue(Resource.success(it))
//            }, {
//                tempLiveData.postValue(getError(it))
//            })
//
//        return tempLiveData
//
//    }
}