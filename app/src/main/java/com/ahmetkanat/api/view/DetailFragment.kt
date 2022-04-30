package com.ahmetkanat.api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ahmetkanat.api.R
import com.ahmetkanat.api.databinding.FragmentDetailBinding
import com.ahmetkanat.api.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel : DetailViewModel
    private var weatherId = 0
    private lateinit var binding : FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            weatherId = DetailFragmentArgs.fromBundle(it).id
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(weatherId)

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { weather ->
            weather?.let {
                binding.selectedWeather = weather
            }
        })

    }

}