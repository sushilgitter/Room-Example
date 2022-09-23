package com.example.roomexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.Room.BookModel
import com.example.roomexample.Room.BookViewModel
import com.example.roomexample.ui.theme.RoomExampleTheme

//Step 6 (optional)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
            RoomExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BookScreen(viewModel = bookViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookScreen(viewModel: BookViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getBooks()
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Books") }) },
        floatingActionButton = { FloatingButton(viewModel = viewModel) },
        content = { padding ->
            BooksContent(
                padding = padding,
                viewModel = viewModel
            )
        }
    )
    if (viewModel.openDialog) {
        BookAlertDialog(viewModel = viewModel)
    }
}


@Composable
@ExperimentalMaterialApi
fun BooksContent(
    padding: PaddingValues,
    viewModel: BookViewModel
) {
    val books = viewModel.books
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(books) { item ->
            BookCard(
                bookModel = item,
                viewModel = viewModel
            )
        }
    }
}


@Composable
fun BookCard(
    bookModel: BookModel,
    viewModel: BookViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxWidth(.9f)) {
                Text(
                    text = bookModel.title,
                    fontSize = 24.sp
                )
                Text(
                    text = "By ${bookModel.author}",
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            IconButton(
                onClick = {viewModel.deleteBook(bookModel)}
            ){
                Icon(Icons.Filled.Delete, contentDescription = "delete")
            }
        }
    }
}

@Composable
fun FloatingButton(viewModel: BookViewModel) {
    FloatingActionButton(
        onClick = { viewModel.openDialog = true },
        backgroundColor = MaterialTheme.colors.primary
    ) { Icon(Icons.Filled.Add, contentDescription = "Add Book") }
}

@Composable
fun BookAlertDialog(viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    if (viewModel.openDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.openDialog = false
            },
            title = { Text(text = "Add a book.") },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = { Text(text = "Title") }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    TextField(
                        value = author,
                        onValueChange = { author = it },
                        placeholder = { Text(text = "Author") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.openDialog = false
                        val book = BookModel(0, title = title, author = author)
                        viewModel.addBook(book)
                        Log.e("MainActivity", "Add button Clicked")
                    }
                ) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    viewModel.openDialog = false
                }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}
