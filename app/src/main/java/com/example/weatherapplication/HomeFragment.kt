package com.example.weatherapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapplication.databinding.FragmentHomeBinding
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var urlDay = "https://api.weatherapi.com/v1/forecast.json?key=9b23ebf98d4b4be1ba481540230404&q=Tashkent&days=7&aqi=no&alerts=no"
    val urlHour = "https://api.weatherapi.com/v1/forecast.json?key=9b23ebf98d4b4be1ba481540230404&q=Tashkent&days=1&aqi=no&alerts=no"

    var hours = mutableListOf<Hour>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val requestQueue = Volley.newRequestQueue(requireContext())
        var hourAdapter = HourAdapter(hours)
        var hourManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hourRv.adapter = hourAdapter
        binding.hourRv.layoutManager = hourManager

        val requestDay = JsonObjectRequest(urlDay, object : Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                val location = response!!.getJSONObject("location")
                binding.location.text = location.getString("region")
                val current = response.getJSONObject("current")
                binding.temperature.text = current.getString("temp_c")
                val condition = current.getJSONObject("condition")
                binding.status.text = condition.getString("text")
            }
        }, object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("TAG", "onErrorResponse: $error")
            }
        })

        val requestHour = JsonObjectRequest(urlHour, object : Response.Listener<JSONObject>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(response: JSONObject?) {
                var forecast = response!!.getJSONObject("forecast")
                var forecastday = forecast.getJSONArray("forecastday")
                for (i in 0 until forecastday.length()){
                    var obj = forecastday.getJSONObject(i)
                    var hourObj = obj.getJSONArray("hour")
                    for (i in 0 until hourObj.length()){
                        var obj1 = hourObj.getJSONObject(i)
                        var time = obj1.getString("time")
                        var temp = obj1.getString("temp_c")
                        var condition = obj1.getJSONObject("condition")
                        var img = "https:" + condition.getString("icon")
                        hours.add(Hour(time, temp, img))
                        hourAdapter.notifyDataSetChanged()


                    }
                }


            }
        }, object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Log.d("TAG", "onErrorResponse: $error")
            }

        })

        requestQueue.add(requestDay)
        requestQueue.add(requestHour)

        return binding.root
    }

}