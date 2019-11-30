package comers_0890.httpsvk.zhssb.di

import android.content.Context
import com.google.gson.Gson
import comers_0890.httpsvk.zhssb.data.preferences.Preferences
import comers_0890.httpsvk.zhssb.data.preferences.PreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val preferencesModule = module {
    single { providePreferences(androidContext(), get()) }
}

fun providePreferences(app: Context, gson: Gson): Preferences {
    return PreferencesImpl(app, gson)
}