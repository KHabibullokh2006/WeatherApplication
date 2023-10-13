package com.example.weatherapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapplication.R
import com.example.weatherapplication.model.Day

class DayAdapter(var dayList:MutableList<Day>, var dayInter:DayInterface) : RecyclerView.Adapter<DayAdapter.DayHolder>(){
    class DayHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var date:TextView = itemView.findViewById(R.id.date)
        var condition:TextView = itemView.findViewById(R.id.day_condition)
        var temp:TextView = itemView.findViewById(R.id.day_temp)
        var icon:ImageView = itemView.findViewById(R.id.day_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        return DayHolder(LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        var day = dayList[position]
        holder.date.text = day.day
        holder.condition.text = day.con_text
        holder.temp.text = ((day.maxtemp_c.toDouble() + day.mintemp_c.toDouble())/2).toString()
        holder.icon.load(day.day_icon)
        holder.itemView.setOnClickListener {
            dayInter.onClick(day)
        }
    }

    interface DayInterface{
        fun onClick(day: Day)
    }

}