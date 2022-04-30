package com.ahmetkanat.api.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkanat.api.R
import com.ahmetkanat.api.adapter.WeatherAdapter
import com.ahmetkanat.api.databinding.FragmentWeatherBinding
import com.ahmetkanat.api.model.Response
import com.ahmetkanat.api.model.Weather
import com.ahmetkanat.api.network.WeatherAPIService
import com.ahmetkanat.api.viewmodel.WeatherViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class WeatherFragment : Fragment() {

    private lateinit var viewModel : WeatherViewModel
    private lateinit var binding : FragmentWeatherBinding
    private val adapter = WeatherAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        searchCity()

        binding.rvWeather.layoutManager = LinearLayoutManager(context)
        binding.rvWeather.adapter = adapter

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.weathers.observe(viewLifecycleOwner, Observer { weather ->
            weather?.let {
                adapter.updateWeatherList(weather)
            }

        })

    }

    private fun searchCity(){

        binding.searchButton.setOnClickListener {
            val city = binding.selectCityText.text.toString()
            viewModel.getDataAPI("tr",city)
        }

    }








}