package com.bignerdranch.android.testtask.core.di

import com.bignerdranch.android.testtask.core.TOKEN
import com.bignerdranch.android.testtask.core.data.GithubApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("Authorization", "token $TOKEN")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        val client = httpClient.build()

        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GithubApi::class.java)
    }
}