package com.example.composeapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    fun getClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 3000
                requestTimeoutMillis = 3000
                socketTimeoutMillis = 3000
            }

            install(DefaultRequest) {
                url {
                    host = "jsonplaceholder.typicode.com"
                    protocol = URLProtocol.HTTPS
                    headers {
                        append(HttpHeaders.Authorization, "SECRET_TOKEN")
                        append(HttpHeaders.ContentType, "application/json")
                    }
                }
            }
        }
    }
}