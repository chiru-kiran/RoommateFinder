package uk.ac.tees.mad.d3614099.data

sealed class SignupUIEvents {
    data class FirstNameChange(val firstName: String) : SignupUIEvents()
    data class LastNameChange(val lastName: String) : SignupUIEvents()
    data class EmailChange(val email: String) : SignupUIEvents()
    data class PasswordChange(val password: String) : SignupUIEvents()

    data object RegistrationButtonClicked : SignupUIEvents()
}
