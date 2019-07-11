package com.example.ktask

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.connection.RouteException
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PlaceApiClient {

    var BaseUrl : String = "https://api.foursquare.com/"

    lateinit var retrofit : Retrofit

    var intercepter  = HttpLoggingInterceptor()
    var okClient = OkHttpClient()


    fun getPlaceApiClient() : Retrofit {

        intercepter?.level
        okClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .addInterceptor(intercepter).build()
        okClient?.retryOnConnectionFailure()

//        var gson : Gson =GsonBuilder()
//                         .setLenient()
//                         .create()

        try {

            retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okClient)
                .build()
        }catch (e : RouteException) {
            e.printStackTrace()
        }

        return retrofit
    }


}