package uk.ac.tees.mad.d3614099.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.d3614099.navigation.Screen
import uk.ac.tees.mad.d3614099.navigation.ScreenRouter
import uk.ac.tees.mad.d3614099.presentation.auth.LoginScreen
import uk.ac.tees.mad.d3614099.presentation.auth.SignUpScreen
import uk.ac.tees.mad.d3614099.presentation.screens.detail.DetailScreen
import uk.ac.tees.mad.d3614099.presentation.screens.home.HomeScreen
//import uk.ac.tees.mad.d3614099.presentation.screens.home.roomDataList
import uk.ac.tees.mad.d3614099.presentation.screens.posts.PostScreen
import uk.ac.tees.mad.d3614099.presentation.screens.posts.PostViewModel
import uk.ac.tees.mad.d3614099.presentation.screens.profile.ProfileScreen

@Composable
fun RoommateFinderApp(postViewModel: PostViewModel = viewModel()) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = ScreenRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SignUp -> {
                    SignUpScreen()
                }

                is Screen.Login -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.ProfileScreen -> {
                    ProfileScreen()
                }


                is Screen.DetailScreen -> {
                    val roomData = (currentState.value as Screen.DetailScreen).room
                    DetailScreen(roomData)
                }

                is Screen.PostScreen -> {
                    PostScreen()
                }
            }
        }
    }
}

