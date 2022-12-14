package com.example.roomexample.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


//Step 2
@Dao
interface BookDao {
    @Query("SELECT * FROM Book_Table ORDER BY ID ASC")
    fun getBooks(): Flow<List<BookModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(bookModel: BookModel)

    @Delete
    suspend fun deleteBook(bookModel: BookModel)
}