package com.example.roomexample.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Step 3
@Database(entities = [BookModel::class], version = 1, exportSchema = false)
abstract class BookDb : RoomDatabase(){
    abstract fun bookDao() :BookDao

    companion object {
        private var INSTANCE: BookDb? = null
        fun getInstance(context: Context): BookDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDb::class.java,
                        "Book_list_database"
                    )
//                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}