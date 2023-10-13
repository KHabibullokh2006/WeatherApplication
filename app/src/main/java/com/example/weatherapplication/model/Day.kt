package com.example.weatherapplication.model

import java.io.Serializable

data class Day (var day:String,var maxtemp_c:String,
                var mintemp_c:String, var con_text:String,
                var day_icon:String ):Serializable