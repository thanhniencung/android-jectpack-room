package com.code4func.android_c4f_room

import androidx.room.TypeConverter
import com.google.gson.Gson

class UserConverter {
    @TypeConverter
    fun stringToUser(data : String) : User {
        return Gson().fromJson(data, User::class.java)
    }

    @TypeConverter
    fun userToString(data : User) : String {
        return Gson().toJson(data)
    }
}