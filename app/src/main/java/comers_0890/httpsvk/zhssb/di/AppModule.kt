package comers_0890.httpsvk.zhssb.di

import comers_0890.httpsvk.zhssb.ui.main.MainViewModel
import comers_0890.httpsvk.zhssb.ui.registration.RegistrationViewModel
import comers_0890.httpsvk.zhssb.ui.scan.ScanViewModelclass
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{ MainViewModel(get(), get()) }
    viewModel{ ScanViewModelclass(get(), get()) }
    viewModel{ RegistrationViewModel(get(), get()) }
}