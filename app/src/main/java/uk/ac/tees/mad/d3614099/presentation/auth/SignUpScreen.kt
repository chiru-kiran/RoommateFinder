package uk.ac.tees.mad.d3614099.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.d3614099.presentation.common.HeadingTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponent
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.data.SignupViewModel
import uk.ac.tees.mad.d3614099.data.SignupUIEvents
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.navigation.SystemBackButtonHandler
import uk.ac.tees.mad.d3614099.presentation.common.ButtonComponent
import uk.ac.tees.mad.d3614099.presentation.common.ClickableLoginTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.PasswordTextFieldComponent


@Composable
fun SignUpScreen(
    loginViewModel: SignupViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvents.FirstNameChange(it))
                    },
                    errorStatus = loginViewModel.registrationUiState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvents.LastNameChange(it))
                    },
                    errorStatus = loginViewModel.registrationUiState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvents.EmailChange(it))
                    },
                    errorStatus = loginViewModel.registrationUiState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        loginViewModel.onEvent(SignupUIEvents.PasswordChange(it))
                    },
                    errorStatus = loginViewModel.registrationUiState.value.passwordError
                )

//            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
//                onTextSelected = {
//                    PostOfficeAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
//                },
//                onCheckedChange = {
//                    signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
//                }
//            )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        loginViewModel.onEvent(SignupUIEvents.RegistrationButtonClicked)
                    },
                    isEnabled = loginViewModel.allResultPassed.value
                )

                Spacer(modifier = Modifier.weight(1f))


                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
//                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
//                navController.navigate("loginScreen")
//                navController.navigate(Route.LoginScreen.route)
                    ScreenRouter.navigateTo(Screen.Login)

                })


//    if(signupViewModel.signUpInProgress.value) {
//        CircularProgressIndicator()

                SystemBackButtonHandler {
                    ScreenRouter.navigateTo(Screen.Login)
                }
            }
        }
        if (loginViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}