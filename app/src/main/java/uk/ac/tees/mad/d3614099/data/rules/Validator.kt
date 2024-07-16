package uk.ac.tees.mad.d3614099.data.rules

object Validator {
    fun validateFirstName(fname: String): ValidationResult {
        return ValidationResult(
            (fname.isNotEmpty() && fname.length > 1)
        )
    }

    fun validateLastName(lname: String): ValidationResult {
        return ValidationResult(
            (lname.isNotEmpty() && lname.length > 1)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length > 5)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)