package comers_0890.httpsvk.zhssb.di

import comers_0890.httpsvk.zhssb.ui.main.MainViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{ MainViewModel(get(), get()) }
}