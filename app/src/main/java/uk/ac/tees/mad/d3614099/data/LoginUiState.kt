package uk.ac.tees.mad.d3614099.data

data class LoginUiState(
    var email: String = "",
    var password: String = "",

    var emailError:Boolean = false,
    var passwordError:Boolean = false
)