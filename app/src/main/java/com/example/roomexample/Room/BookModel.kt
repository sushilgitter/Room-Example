package com.example.roomexample.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


//Step 1
// Add Room Dependencies to build.gradle file
@Entity(tableName = "Book_table")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String
)


