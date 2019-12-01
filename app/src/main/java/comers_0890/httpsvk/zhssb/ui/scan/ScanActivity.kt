package comers_0890.httpsvk.zhssb.ui.scan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import comers_0890.httpsvk.zhssb.MainActivity
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

        scan_token.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 8 && (p0.toString() == "8ff3jxwW")){
                    scan_token.setTextColor(resources.getColor(R.color.white))
                    scan_token.setBackgroundColor(resources.getColor(R.color.red))
                    Handler().postDelayed({
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }, 1000)
                }
            }

        })
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                    val p = barcode.cornerPoints

                    scan_token.setText("8ff3jxwW")
                    scan_token.setTextColor(resources.getColor(R.color.white))
                    scan_token.setBackgroundColor(resources.getColor(R.color.red))
                    Handler().postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                    }, 1000)
//                    Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
//                    val launchIntent =
//                        packageManager.getLaunchIntentForPackage("kz.sdk.hcsbk")
//                    if (launchIntent != null) {
//                        startActivity(launchIntent)//null pointer check in case package name was not found
//                    }

//                    mResultTextView.text = barcode.displayValue
                } else {
//                    mResultTextView.setText(R.string.no_barcode_captured)
                }
            } else
                Log.e(
                    LOG_TAG, String.format(
                        getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)
                    )
                )
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }


    companion object {
        private val LOG_TAG = ScanActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }
}
