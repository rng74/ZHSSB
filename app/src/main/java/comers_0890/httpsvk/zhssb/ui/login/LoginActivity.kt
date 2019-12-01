package comers_0890.httpsvk.zhssb.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comers_0890.httpsvk.zhssb.MainActivity
import comers_0890.httpsvk.zhssb.R
import comers_0890.httpsvk.zhssb.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            if (login_text.text!!.isEmpty()) {
                login.error = "Заполните все поля"
            }
            if (password_text.text!!.isEmpty()) {
                password.error = "Заполните все поля"
            }
            if (!login_text.text!!.isEmpty() && !password_text.text!!.isEmpty()) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

        registration_button.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
