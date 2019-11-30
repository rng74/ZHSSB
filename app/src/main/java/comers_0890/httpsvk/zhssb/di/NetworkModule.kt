package comers_0890.httpsvk.zhssb.di

import comers_0890.httpsvk.zhssb.network.API
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
private val CONNECTION_TIMEOUT_IN_SECONDS = 30
private val READ_TIMEOUT_IN_SECONDS = 30
private val WRITE_TIMEOUT_IN_SECONDS = 30

val networkModule = module {
    single { provideDefaultOkHttpClient() }
    single { provideGson() }
    single { provideRxJava2CallAdapterFactory() }
    single { provideGsonConverterFactory(get()) }
    single { provideRetrofit(get(), get(), get()) }
    single { provideAPIService(get()) }
}

fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .build()
}

fun provideGson(): Gson {
    return Gson()
}
fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

fun provideRetrofit(
    client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://167.71.43.93/api/")
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()
}

fun provideAPIService(retrofit: Retrofit): API = retrofit.create(API::class.java)