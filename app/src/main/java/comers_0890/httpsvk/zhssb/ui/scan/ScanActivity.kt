package comers_0890.httpsvk.zhssb.ui.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import comers_0890.httpsvk.zhssb.R
import comers_0890.httpsvk.zhssb.qr.BarcodeCaptureActivity
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scan_button.setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE)
        }
    }

    companion object {
        private val LOG_TAG = ScanActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }
}
