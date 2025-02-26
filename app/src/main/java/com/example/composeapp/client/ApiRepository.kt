package com.example.composeapp.client

import com.example.composeapp.model.QuotesResponse

class ApiRepository(private val apiService: KtorApiService) {
    suspend fun getQuotes(): ApiResponse<QuotesResponse> {
        return apiService.fetchData("/quotes")
    }
}