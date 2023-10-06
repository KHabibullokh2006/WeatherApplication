package com.example.weatherapplication

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HourAdapter(var forecastList:MutableList<HourForecast>) : RecyclerView.Adapter<HourAdapter.WeatherHolder>() {

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        TODO("Not yet implemented")
    }

}