package com.ahmetkanat.api.network

import com.ahmetkanat.api.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {


    @Headers(
        "authorization: apikey 4Qy4DUtU9q4myB0iNGnT4f:2NnI83FqdvQR8PNw6vnPYo",
        "content-type: application/json"
    )
    @GET("getWeather")
    fun getWeatherAPI(
        @Query("data.lang") lang : String,
        @Query("data.city") city : String
    ) : Single<Response>


}