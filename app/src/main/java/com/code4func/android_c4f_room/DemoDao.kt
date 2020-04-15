package com.code4func.android_c4f_room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DemoDao {
    @Insert
    suspend fun insertUser(vararg users: User)

    @Insert
    suspend fun insertProduct(vararg products: Product)

    @Insert
    suspend fun insertOrders(vararg orders: Order)

    @Query("SELECT * FROM products")
    suspend fun loadAllProduct(): List<Product>

    @Query("SELECT * FROM users")
    suspend fun loadAllUsers(): List<User>

    @Transaction
    @Query("SELECT * FROM users")
    fun getUserWithOrders(): List<UserWithOrders>
}