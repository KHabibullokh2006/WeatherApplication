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
import com.example.weatherapplication.adapter.DayAdapter
import com.example.weatherapplication.adapter.HourAdapter
import com.example.weatherapplication.databinding.FragmentHomeBinding
import com.example.weatherapplication.model.Day
import com.example.weatherapplication.model.Hour
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private var urlDay = "https://api.weatherapi.com/v1/forecast.json?key=9bcfb053b7d247fda8c53154230810&q=Tashkent&days=7&aqi=yes&alerts=yes"
    private val urlHour = "https://api.weatherapi.com/v1/forecast.json?key=9b23ebf98d4b4be1ba481540230404&q=Tashkent&days=2&aqi=no&alerts=no"

    var hours = mutableListOf<Hour>()
    var days = mutableListOf<Day>()

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

        val dayAdapter = DayAdapter(days, object : DayAdapter.DayInterface{
            override fun onClick(day: Day) {
                var bundle = Bundle()
                bundle.putSerializable("day", day)
                var second = BlankFragment()
                second.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, second)
                    .commit()
            }
        })
        val dayManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.dayRv.adapter = dayAdapter
        binding.dayRv.layoutManager = dayManager

        val requestDay = JsonObjectRequest(urlDay, object : Response.Listener<JSONObject>{
            @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
            override fun onResponse(response: JSONObject?) {
                val location = response!!.getJSONObject("location")
                binding.location.text = location.getString("region")
                val current = response.getJSONObject("current")
                binding.temperature.text = current.getString("temp_c") + "°"
                val condition = current.getJSONObject("condition")
                binding.status.text = condition.getString("text")

                var forecast = response.getJSONObject("forecast")
                var forecastday = forecast.getJSONArray("forecastday")
                for (i in 0 until forecastday.length()){
                    var obj = forecastday.getJSONObject(i)
                    var date = obj.getString("date")
                    val day = obj.getJSONObject("day")
                    var maxtemp_c = day.getString("maxtemp_c").toDouble().roundToInt().toString()
                    var mintemp_c = day.getString("mintemp_c").toDouble().roundToInt().toString()
                    var condition = day.getJSONObject("condition")
                    var con_text = condition.getString("text")
                    var icon = "https:" + condition.getString("icon")
                    days.add(Day(date, maxtemp_c, mintemp_c, con_text, icon))
                    dayAdapter.notifyDataSetChanged()
                }
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

                Log.d("AAA", forecastday.length().toString())

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
                        Log.d("TAG", "CONGRATULATIONS ")
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