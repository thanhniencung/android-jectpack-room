package com.code4func.android_c4f_room

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "user_id")
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "display_name")
    var displayName: String,

    @Ignore var avatar: String) {

    constructor(userId: Int, displayName: String) : this(userId, displayName, "")
}

// one to many
data class UserWithOrders(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val orders: List<Order>
)