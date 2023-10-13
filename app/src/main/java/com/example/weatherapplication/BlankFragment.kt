package com.example.weatherapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.weatherapplication.databinding.FragmentBlankBinding
import com.example.weatherapplication.model.Day

class BlankFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBlankBinding.inflate(inflater, container, false)
        var item = this.arguments?.getSerializable("day") as Day

        binding.date.text = item.day
        binding.condition.text = item.con_text
        binding.temp.text = ((item.maxtemp_c.toDouble() + item.mintemp_c.toDouble())/2).toString()
        binding.img.load(item.day_icon)

        return binding.root
    }

}