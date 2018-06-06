package com.yjy.redmine

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

class Retrofits private constructor(

) {

    val retrofit: Retrofit

    init {

        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain: Interceptor.Chain? ->
                val origin = chain!!.request()
                val request = origin.newBuilder()
                    .addHeader("X-Redmine-Switch-User", "YuanJunYao")
                    .addHeader("X-Redmine-API-Key", "a5564509fcbcf7c4e437d81789df36b05b9eb823")
                    .method(origin.method(), origin.body())
                    .build()
                chain.proceed(request)
            }
            .build()

        val gson = GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(DateFormat.LONG)
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://redmine.jbangit.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    companion object {

        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Retrofits()
        }
    }

}
