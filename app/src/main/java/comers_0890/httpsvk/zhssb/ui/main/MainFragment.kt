package comers_0890.httpsvk.zhssb.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import comers_0890.httpsvk.zhssb.R
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.prefs.Preferences

class MainFragment : Fragment() {
    val preferences: Preferences by inject()
    private val viewModel: MainViewModel by viewModel()
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        initViewModel()

    }
    private fun initViewModel() {

    }
}