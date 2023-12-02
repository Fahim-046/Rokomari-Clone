package com.example.rokomaribookapp.utils.extentions

import androidx.room.TypeConverter
import com.example.rokomaribookapp.models.Cart
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromCartListToString(value: List<Cart>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Cart>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToCartList(value: String): List<Cart> {
        val gson = Gson()
        val type = object : TypeToken<List<Cart>>() {}.type
        return gson.fromJson(value, type)
    }
}
