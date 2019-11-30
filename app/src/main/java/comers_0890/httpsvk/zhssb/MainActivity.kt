package comers_0890.httpsvk.zhssb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.vision.barcode.Barcode
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.takusemba.spotlight.OnSpotlightStateChangedListener
import com.takusemba.spotlight.OnTargetStateChangedListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.target.SimpleTarget
import comers_0890.httpsvk.zhssb.qr.BarcodeCaptureActivity
import comers_0890.httpsvk.zhssb.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private val mainFragment: MainFragment = MainFragment()
//    private val operaFragment: OperaFragment = OperaFragment()
//    private val contactsFragment: ContactsFragment = ContactFragment()
//    private val moreFragment: MoreFragment = MoreFragment()

    private val activeFragment: Fragment = mainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("MyTag", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = String.format(getString(R.string.str, token))
                Log.d("MyTag", msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })

        start_qr.setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE)
        }

        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        prepareGraph()

        initView()
//        spotLight()
    }

    private fun prepareGraph() {
        val entry = mutableListOf<PieEntry>()
        entry.add(PieEntry(13.0f, "Заплатил"))
        entry.add(PieEntry(87.0f, "Остаток"))

        val pieDataSet = PieDataSet(entry, "")

        pieDataSet.valueTextSize = 12f

        this.let { context ->
            pieDataSet.colors = getColors(context)
            pieDataSet.valueTextColor = ContextCompat.getColor(context, android.R.color.white)
        }

        vp_pie_chart.data = PieData(pieDataSet)

        //TODO : Add description

    }

    private fun getColors(context: Context): MutableList<Int> {
        return mutableListOf(
            ContextCompat.getColor(context, android.R.color.holo_blue_bright),
            ContextCompat.getColor(context, android.R.color.darker_gray),
            ContextCompat.getColor(context, android.R.color.holo_orange_light),
            ContextCompat.getColor(context, android.R.color.holo_orange_dark),
            ContextCompat.getColor(context, android.R.color.holo_red_dark),
            ContextCompat.getColor(context, android.R.color.holo_red_light),
            ContextCompat.getColor(context, android.R.color.holo_purple),
            ContextCompat.getColor(context, android.R.color.holo_blue_dark),
            ContextCompat.getColor(context, android.R.color.holo_green_light),
            ContextCompat.getColor(context, android.R.color.holo_green_dark),
            ContextCompat.getColor(context, android.R.color.holo_blue_light)
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                    val p = barcode.cornerPoints
                    Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
                    val launchIntent =
                        packageManager.getLaunchIntentForPackage("kz.sdk.hcsbk")
                    if (launchIntent != null) {
                        startActivity(launchIntent)//null pointer check in case package name was not found
                    }

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
        private val LOG_TAG = MainActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }


    private fun spotLight() {
        val x = 1260
        val y = 2378

        val simpleTarget = SimpleTarget.Builder(this@MainActivity)
            .setPoint(x.toFloat(), y.toFloat())
            .setShape(Circle(140f)) // or RoundedRectangle()
            .setTitle("Сканировать QR")
            .setDescription("Сканируй QR своего реферала и получай бонусы!")
            .setOnSpotlightStartedListener(object : OnTargetStateChangedListener<SimpleTarget> {
                override fun onStarted(target: SimpleTarget) {
                    // do something
                }

                override fun onEnded(target: SimpleTarget) {
                    // do something
                }
            })
            .build()

        Spotlight.with(this@MainActivity)
            .setOverlayColor(R.color.background)
            .setDuration(1000L)
            .setTargets(simpleTarget)
            .setAnimation(DecelerateInterpolator(2f))
            .setClosedOnTouchedOutside(true)
            .setOnSpotlightStateListener(object : OnSpotlightStateChangedListener {

                override fun onStarted() {

                }

                override fun onEnded() {}
            })
            .start()
    }

    private fun initView() {}
}
