package com.example.weatherapplication.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.model.Day

class DayAdapter(var dayList:MutableList<Day>) : RecyclerView.Adapter<DayAdapter.DayHolder>(){
    class DayHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        TODO("Not yet implemented")
    }

}