package com.example.roomexample.Room

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


//Step 5 (optional)
class BookViewModel (appObj: Application): AndroidViewModel(appObj) {

    private val repo: BookRepository = BookRepository(appObj)
    var books by mutableStateOf(emptyList<BookModel>())
    var openDialog by mutableStateOf(false)

    fun getBooks() {
        viewModelScope.launch {
            repo.getBooksFromRoom().collect { response ->
                books = response
            }
        }
    }
    fun addBook(book: BookModel) {
        viewModelScope.launch {
            repo.addBookToRoom(book)
        }
    }
    fun deleteBook(book: BookModel) {
        viewModelScope.launch {
            repo.deleteBookInRoom(book)
        }
    }
}