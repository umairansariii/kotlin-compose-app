package com.example.composeapp.auth.domain.usecases

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password is required",
            )
        }

        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 8 characters"
            )
        }

        val passwordPattern = Regex(pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).+\$")

        if (!password.matches(passwordPattern)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password is not strong enough"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}