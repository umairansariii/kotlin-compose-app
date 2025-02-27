package com.example.composeapp.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeapp.data.model.Post

@Composable
fun HomeScreen() {
    val viewModel = HomeViewModel()
    var posts by rememberSaveable { mutableStateOf(emptyList<Post>()) }

    LaunchedEffect(Unit) {
        posts = viewModel.getPosts()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(posts) { post ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = post.id.toString())
                    Spacer(Modifier.height(8.dp))
                    Text(text = post.title, style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    Text(text = post.body, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}