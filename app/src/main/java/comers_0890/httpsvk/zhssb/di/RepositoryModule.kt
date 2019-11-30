package comers_0890.httpsvk.zhssb.di

import com.google.gson.Gson
import comers_0890.httpsvk.zhssb.data.preferences.Preferences
import comers_0890.httpsvk.zhssb.data.repository.Repository
import comers_0890.httpsvk.zhssb.data.repository.Repositorylmpl
import comers_0890.httpsvk.zhssb.network.API
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepository(get(), get(), get()) }
}

fun provideRepository(api: API, preferences: Preferences, gson: Gson): Repository {
    return Repositorylmpl(api, preferences, gson)
}