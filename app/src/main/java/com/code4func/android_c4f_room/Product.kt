package com.code4func.android_c4f_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId: Int = 0,

    @ColumnInfo(name = "product_name")
    var productName: String
)