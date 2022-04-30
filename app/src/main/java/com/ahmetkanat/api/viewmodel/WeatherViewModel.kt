package com.ahmetkanat.api.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ahmetkanat.api.model.Response
import com.ahmetkanat.api.model.Weather
import com.ahmetkanat.api.network.WeatherAPIService
import com.ahmetkanat.api.roomdb.WeatherDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : BaseViewModel(application) {

    private val weatherAPIService = WeatherAPIService()
    private val compositeDisposable = CompositeDisposable()
    val weathers = MutableLiveData<List<Weather>>()

    fun getDataAPI(lang : String,city : String){

        compositeDisposable.add(
            weatherAPIService.getData(lang,city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response>(){
                    override fun onSuccess(t: Response) {
                        val tempList = t.result
                        tempList as ArrayList<Weather>
                        insertSQL(tempList)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(),"Veri Yok",Toast.LENGTH_SHORT).show()
                    }

                })

        )
    }

    fun insertSQL(list : List<Weather>){

        launch {
            val dao = WeatherDatabase(getApplication()).weatherDao()
            dao.deleteALlWeather()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i += 1
            }

            weathers.value = list
        }

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}