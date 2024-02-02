package com.example.khushibaby.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromString(value: String): ArrayList<Int> {
            val retType = object : TypeToken<ArrayList<Int>>() {}.type
            return Gson().fromJson(value, retType)
        }

        @TypeConverter
        @JvmStatic
        fun fromList(list: ArrayList<Int>): String {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            return gson.toJson(list, type)
        }

        @JvmStatic
        @TypeConverter
        fun fromStringToMap(value: String): Map<String, String> {
            val mapType = object : TypeToken<Map<String, String>>() {}.type
            return Gson().fromJson(value, mapType)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToMap(map: Map<String, String>): String {
            val gson = Gson()
            val type = object : TypeToken<Map<String, String>>() {}.type
            return gson.toJson(map, type)
        }
    }
}