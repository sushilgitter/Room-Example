package com.example.roomexample.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book_table")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String
)