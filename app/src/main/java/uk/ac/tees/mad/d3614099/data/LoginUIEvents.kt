package uk.ac.tees.mad.d3614099.data

sealed class LoginUIEvents {
    data class EmailChange(val email: String) : LoginUIEvents()
    data class PasswordChange(val password: String) : LoginUIEvents()

    data object LoginButtonClicked : LoginUIEvents()
}
