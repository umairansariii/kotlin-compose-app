package com.example.composeapp.auth.domain.usecases

class ValidateLastName {
    fun execute(lastName: String): ValidationResult {
        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Last name is required",
            )
        }

        val isAlpha = lastName.all { it.isLetter() }

        if (!isAlpha) {
            return ValidationResult(
                successful = false,
                errorMessage = "Last name must contain only letters",
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}