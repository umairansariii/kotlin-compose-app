package com.example.composeapp.auth.domain.usecases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
