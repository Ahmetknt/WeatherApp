package com.ahmetkanat.api.network

import com.ahmetkanat.api.model.Response
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class WeatherAPIService {

    val BASE_URL = "https://api.collectapi.com/weather/"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getData(lang : String,city : String) : Single<Response>{
        return api.getWeatherAPI(lang,city)
    }


}