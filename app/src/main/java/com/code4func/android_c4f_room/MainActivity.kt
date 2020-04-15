package com.code4func.android_c4f_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db: AppDatabase by lazy {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")
            }
        }

        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "room-db.db"
        )
        //.addMigrations(MIGRATION_2_3)
        .fallbackToDestructiveMigration()
        .build()
    }

    // Offical => https://developer.android.com/training/data-storage/room
    // Tools sqlite => https://proandroiddev.com/android-sqlite-debug-tools-8b5e2d9a56b2
    // https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setupData()
        //selectData()
        //testOneToMany()
    }

    fun testOneToMany() {
        CoroutineScope(Dispatchers.IO).launch {
            val a = db.demoDao().getUserWithOrders()
            val b = 1
        }
    }

    fun setupData() {
        CoroutineScope(Dispatchers.IO).launch {
            // insert user
            db.demoDao().insertUser(
                User(
                    displayName = "Ryan",
                    avatar = "ryan.png"
                ),
                User(
                    displayName = "Alice",
                    avatar = "alice.png"
                ),
                User(
                    displayName = "Bob",
                    avatar = "bob.png"
                )
            )

            // insert product
            db.demoDao().insertProduct(
                Product(
                    productName = "Android Programming"
                ),
                Product(
                    productName = "iOS Programming"
                ),
                Product(
                    productName = "Flutter Programming"
                )
            )

            // insert orders
            val listProduct = db.demoDao().loadAllProduct()
            val listUser = db.demoDao().loadAllUsers()

            for (i in 0..20) {
                val indexUser = listUser.indices.random()
                val indexProduct = listProduct.indices.random()

                db.demoDao().insertOrders(
                    Order(
                        userId = listUser[indexUser].userId,
                        productId = listProduct[indexProduct].productId,
                        productName = listProduct[indexProduct].productName,
                        quantity = (1..50).random(),
                        price = (100..100000).random().toDouble(),
                        user = listUser[indexUser]
                    )
                )
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun selectData() {
        CoroutineScope(Dispatchers.IO).launch {
            val listProduct = db.demoDao().loadAllProduct()
            val listUser = db.demoDao().loadAllUsers()

            listUser.forEach {
                println(it.toString())
            }

            listProduct.forEach {
                println(it.toString())
            }


            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
