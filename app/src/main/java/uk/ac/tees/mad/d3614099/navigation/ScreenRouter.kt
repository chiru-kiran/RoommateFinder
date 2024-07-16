package uk.ac.tees.mad.d3614099.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import uk.ac.tees.mad.d3614099.presentation.screens.home.RoomData

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object HomeScreen : Screen("home")
    data object ProfileScreen : Screen("profile")

    data object PostScreen : Screen("post")
    data class DetailScreen(val room: RoomData) : Screen("detail/{roomId}") {
        companion object {
            fun createRoute(room: RoomData) = "detail/${room.id}"
        }
    }
}

object ScreenRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Login)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }

    fun navigateToDetailScreen(room: RoomData) {
        currentScreen.value = Screen.DetailScreen(room)
    }
}
