package com.example.composeapp.client

import com.example.composeapp.model.Quote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}

class KtorApiService {
    companion object {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
                socketTimeoutMillis = 3000
            }

            install(DefaultRequest) {
                url {
                    host = "dummyjson.com"
                    protocol = URLProtocol.HTTPS
                    headers {
                        append(HttpHeaders.Authorization, "SECRET_TOKEN")
                    }
                }
            }
        }
    }

    suspend fun getQuotes(): List<Quote> {
        return client.get("/quotes").body<List<Quote>>()
    }

    suspend inline fun <reified T> fetchData(endpoint: String): ApiResponse<T> {
        return try {
            val response: T = client.get(endpoint).body()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}