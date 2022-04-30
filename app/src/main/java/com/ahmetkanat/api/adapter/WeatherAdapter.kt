package com.ahmetkanat.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkanat.api.R
import com.ahmetkanat.api.databinding.CardTasarimBinding
import com.ahmetkanat.api.model.Weather
import com.ahmetkanat.api.view.WeatherFragmentDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_tasarim.view.*

class WeatherAdapter(val weatherList : ArrayList<Weather>) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>(),WeatherListener {


    class WeatherHolder(val view : CardTasarimBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CardTasarimBinding>(layoutInflater,R.layout.card_tasarim,parent,false)
        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.view.weather = weatherList[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onWeatherClicked(v: View) {
        val uuid = v.weatherUUID.text.toString().toInt()
        val action = WeatherFragmentDirections.actionWeatherFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateWeatherList(newWeatherList : List<Weather>){
        weatherList.clear()
        weatherList.addAll(newWeatherList)
        notifyDataSetChanged()
    }


}