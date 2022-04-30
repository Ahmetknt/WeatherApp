package com.ahmetkanat.api.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ahmetkanat.api.model.Weather
import com.ahmetkanat.api.roomdb.WeatherDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val weatherLiveData = MutableLiveData<Weather>()

    fun getDataFromRoom(uuid : Int){

        launch {
            val dao = WeatherDatabase(getApplication()).weatherDao()
            val weather = dao.getWeather(uuid)
            weatherLiveData.value = weather
        }

    }






}