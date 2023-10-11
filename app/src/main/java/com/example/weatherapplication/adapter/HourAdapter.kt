package com.example.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapplication.R
import com.example.weatherapplication.model.Hour

class HourAdapter(var hourList:MutableList<Hour>) : RecyclerView.Adapter<HourAdapter.WeatherHolder>() {

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var time:TextView = itemView.findViewById(R.id.hour_time)
        var temp:TextView = itemView.findViewById(R.id.hour_temp)
        var icon:ImageView = itemView.findViewById(R.id.hour_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(LayoutInflater.from(parent.context).inflate(R.layout.hour_item, parent, false))
    }

    override fun getItemCount(): Int {
        return hourList.size
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        var hour = hourList[position]
        holder.time.text = hour.time
        holder.temp.text = hour.temperature
        holder.icon.load(hour.icon)
    }

}