package comers_0890.httpsvk.zhssb.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import comers_0890.httpsvk.zhssb.data.ResponseError.Resource
import comers_0890.httpsvk.zhssb.data.models.RegistrationQueryBody
import comers_0890.httpsvk.zhssb.data.models.RegistrationResponse
import comers_0890.httpsvk.zhssb.data.preferences.Preferences
import comers_0890.httpsvk.zhssb.data.repository.Repository

class ScanViewModelclass (repository: Repository, preferences: Preferences) : ViewModel(){
    private val _sendRegistrationResource = MutableLiveData<RegistrationQueryBody>()
    val sendRegistration : LiveData<Resource<RegistrationResponse>>? = Transformations
        .switchMap(_sendRegistrationResource) {
            repository.getRegistration(it)
        }

    fun sendRegistration(body: RegistrationQueryBody){
        _sendRegistrationResource.postValue(body)
    }
}