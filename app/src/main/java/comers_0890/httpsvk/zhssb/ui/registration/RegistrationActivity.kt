package comers_0890.httpsvk.zhssb.ui.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import comers_0890.httpsvk.zhssb.R
import comers_0890.httpsvk.zhssb.ui.scan.ScanActivity
import kotlinx.android.synthetic.main.registration_layout.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        next.setOnClickListener {
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivity(intent)
        }
    }
}