package fr.airweb.airwebtest.api.di

import fr.airweb.airwebtest.api.AuthInterceptor
import fr.airweb.airwebtest.utils.Constants
import fr.airweb.airwebtest.api.PsgApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    factory { providePsgApi(get()) }
    single { provideRetrofit(get()) }
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun providePsgApi(retrofit: Retrofit): PsgApi = retrofit.create(PsgApi::class.java)