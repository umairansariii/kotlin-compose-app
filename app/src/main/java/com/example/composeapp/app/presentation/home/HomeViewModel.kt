package com.example.composeapp.app.presentation.home

import androidx.lifecycle.ViewModel
import com.example.composeapp.data.model.Post
import com.example.composeapp.data.remote.PostRepository

class HomeViewModel(): ViewModel() {
    private val postRepository = PostRepository()

    suspend fun getPosts(): List<Post> {
        return postRepository.fetchPosts()
    }
}