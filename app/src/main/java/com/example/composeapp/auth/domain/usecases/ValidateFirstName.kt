package com.example.composeapp.auth.domain.usecases

class ValidateFirstName {
    fun execute(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "First name is required",
            )
        }

        val isAlpha = firstName.all { it.isLetter() }

        if (!isAlpha) {
            return ValidationResult(
                successful = false,
                errorMessage = "First name must contain only letters",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}