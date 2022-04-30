package com.ahmetkanat.api.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ahmetkanat.api.model.Weather

@Dao
interface WeatherDAO {


    @Insert
    suspend fun insertAll(vararg weather : Weather) : List<Long>

    @Query("SELECT * FROM Weather")
    suspend fun getAllWeather() : List<Weather>

    @Query("DELETE FROM Weather")
    suspend fun deleteALlWeather()

    @Query("SELECT * FROM Weather WHERE uuid = :weatherId")
    suspend fun getWeather(weatherId : Int) : Weather


}