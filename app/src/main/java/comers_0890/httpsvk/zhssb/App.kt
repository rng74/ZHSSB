package comers_0890.httpsvk.zhssb

import android.app.Application
import comers_0890.httpsvk.zhssb.di.appModule
import comers_0890.httpsvk.zhssb.di.networkModule
import comers_0890.httpsvk.zhssb.di.preferencesModule
import comers_0890.httpsvk.zhssb.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(preferencesModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(appModule)
        }
    }
}