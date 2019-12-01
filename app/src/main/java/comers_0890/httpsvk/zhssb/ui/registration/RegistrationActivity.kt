package comers_0890.httpsvk.zhssb.ui.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import comers_0890.httpsvk.zhssb.R
import comers_0890.httpsvk.zhssb.data.ResponseError.Status
import comers_0890.httpsvk.zhssb.data.models.RegistrationQueryBody
import comers_0890.httpsvk.zhssb.data.models.RegistrationResponse
import comers_0890.httpsvk.zhssb.ui.scan.ScanActivity
import comers_0890.httpsvk.zhssb.ui.scan.ScanViewModelclass
import kotlinx.android.synthetic.main.registration_layout.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.prefs.Preferences

class RegistrationActivity : AppCompatActivity() {
    val preferences: Preferences by inject()
    private val viewModel: RegistrationViewModel by viewModel()

    lateinit var body: RegistrationQueryBody

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        observeViewModel()

        next.setOnClickListener {
//            viewModel.sendRegistration(body)
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivity(intent)
        }

        initRegistrationResponse()

    }

    private fun initRegistrationResponse(){
        body = RegistrationQueryBody(number_text.text.toString(), name_text.text.toString(), surname_text.text.toString(), "","1234")
    }

    private fun observeViewModel(){
        viewModel.sendRegistration!!.observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    Log.d("UnSuccessposter","Yes")
                    //                    viewModel.handleLoading()
                }
                Status.SUCCESS -> {
                    Log.d("Successposter","Yes")
                }
                Status.ERROR -> {
                    Log.d("UnSuccessposter","Error")
                }
            }
        })

    }
}