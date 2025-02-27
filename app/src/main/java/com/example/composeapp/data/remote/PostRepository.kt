package com.example.composeapp.data.remote

import com.example.composeapp.data.model.Post
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostRepository() {
    private val client = KtorClient().getClient()

    suspend fun fetchPosts(): List<Post> {
        return try {
            client.get("/posts").body()
        } catch (e: Exception) {
            emptyList()
        }
    }
}