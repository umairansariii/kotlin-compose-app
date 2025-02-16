package com.example.composeapp.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.auth.domain.usecases.ValidateEmail
import com.example.composeapp.auth.domain.usecases.ValidateFirstName
import com.example.composeapp.auth.domain.usecases.ValidateLastName
import com.example.composeapp.auth.domain.usecases.ValidatePassword
import com.example.composeapp.auth.domain.usecases.ValidateRepeatedPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
): ViewModel() {
    var state by mutableStateOf(RegisterFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName, firstNameError = null)
            }

            is RegisterFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName, lastNameError = null)
            }

            is RegisterFormEvent.EmailChanged -> {
                state = state.copy(email = event.email, emailError = null)
            }

            is RegisterFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is RegisterFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword, repeatedPasswordError = null)
            }

            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(firstName = state.firstName)
        val lastNameResult = validateLastName.execute(lastName = state.lastName)
        val emailResult = validateEmail.execute(email = state.email)
        val passwordResult = validatePassword.execute(password = state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            password = state.password, repeatedPassword = state.repeatedPassword
        )

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}