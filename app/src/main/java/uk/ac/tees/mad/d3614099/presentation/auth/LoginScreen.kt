package uk.ac.tees.mad.d3614099.presentation.auth

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.d3614099.R
import uk.ac.tees.mad.d3614099.data.LoginUIEvents
import uk.ac.tees.mad.d3614099.data.LoginViewModel
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.common.ButtonComponent
import uk.ac.tees.mad.d3614099.presentation.common.ClickableLoginTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.HeadingTextComponent
import uk.ac.tees.mad.d3614099.presentation.common.MyTextFieldComponent
import uk.ac.tees.mad.d3614099.presentation.common.PasswordTextFieldComponent
import uk.ac.tees.mad.d3614099.presentation.common.UnderLinedTextComponent

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = loginViewModel.errorMessage.value) {
        loginViewModel.errorMessage.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

//                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvents.EmailChange(it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvents.PasswordChange(it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.passwordError
                )

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvents.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allResultPassed.value
                )

                Spacer(modifier = Modifier.weight(1f))


                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
//                    navController.navigate(Route.SignUpScreen.route)
                    ScreenRouter.navigateTo(Screen.SignUp)
                })
            }
        }
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}