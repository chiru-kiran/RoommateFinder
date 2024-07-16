package uk.ac.tees.mad.d3614099.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import uk.ac.tees.mad.d3614099.data.rules.Validator
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter

class SignupViewModel : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName

    var registrationUiState = mutableStateOf(SignupUiState())

    var allResultPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvents) {
        validateDataWithRules()
        when (event) {
            is SignupUIEvents.FirstNameChange -> {
                registrationUiState.value =
                    registrationUiState.value.copy(firstName = event.firstName)
                printState()

            }

            is SignupUIEvents.LastNameChange -> {
                registrationUiState.value =
                    registrationUiState.value.copy(lastName = event.lastName)
                printState()
            }

            is SignupUIEvents.EmailChange -> {
                registrationUiState.value = registrationUiState.value.copy(email = event.email)
                printState()
            }

            is SignupUIEvents.PasswordChange -> {
                registrationUiState.value =
                    registrationUiState.value.copy(password = event.password)
                printState()
            }

            is SignupUIEvents.RegistrationButtonClicked -> {
                printButtonState()
            }
        }
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fname = registrationUiState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lname = registrationUiState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registrationUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUiState.value.password
        )

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        //store the result of all the validation
        allResultPassed.value =
            fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status
    }

    private fun printButtonState() {
        Log.d(TAG, "Inside_printButtonState")
        Log.d(TAG, registrationUiState.value.toString())
        storeUserInFirebase(
            email = registrationUiState.value.email,
            password = registrationUiState.value.password
        )
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUiState.value.toString())
    }

    private fun storeUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true
        //For this import the firebase auth library
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "inside_OnComplete_Listener")
                Log.d(TAG, "is successful = ${it.isSuccessful}")

                signUpInProgress.value = false

                if (it.isSuccessful)
                    ScreenRouter.navigateTo(Screen.HomeScreen)
            }
            .addOnFailureListener {
                Log.d(TAG, "inside_OnFailure_Listener")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
                signUpInProgress.value = false

            }
    }

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside Signout Success ")
                ScreenRouter.navigateTo(Screen.Login)
            } else {
                Log.d(TAG, "Inside Signout Failure ")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}