package com.code4func.android_c4f_room

import androidx.room.*

@Entity(tableName = "orders")
@TypeConverters(UserConverter::class)
data class Order(
    @PrimaryKey(autoGenerate = true)
    var orderId: Int = 0,

    @ColumnInfo(name = "user_id")
    var userId: Int,

    @ColumnInfo(name = "product_id")
    var productId: Int,

    @ColumnInfo(name = "product_name")
    var productName: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "price")
    var price: Double,

    @ColumnInfo(name = "user_details")
    var user: User
)