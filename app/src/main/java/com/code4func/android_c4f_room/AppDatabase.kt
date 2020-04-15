package com.code4func.android_c4f_room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    User::class,
    Product::class,
    Order::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun demoDao(): DemoDao
}