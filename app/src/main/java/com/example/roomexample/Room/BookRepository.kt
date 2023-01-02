package com.example.roomexample.Room

import android.app.Application
import kotlinx.coroutines.flow.Flow


// Step 4
class BookRepository(application: Application) {
    private var bookDao: BookDao

    init {
        val database = BookDb.getInstance(application)
        bookDao = database.bookDao()
    }

    fun getBooksFromRoom(): Flow<List<BookModel>> = bookDao.getBooks()
    suspend fun addBookToRoom(bookModel: BookModel) = bookDao.addBook(bookModel)
    suspend fun deleteBookInRoom(bookModel: BookModel) = bookDao.deleteBook(bookModel)

}