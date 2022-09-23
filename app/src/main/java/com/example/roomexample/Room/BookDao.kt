package com.example.roomexample.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


//Step 2
@Dao
interface BookDao {
    @Query("SELECT * FROM Book_Table ORDER BY ID ASC")
    fun getBooks() : Flow<List<BookModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(bookModel: BookModel)

    @Delete
    fun deleteBook(bookModel: BookModel)
}