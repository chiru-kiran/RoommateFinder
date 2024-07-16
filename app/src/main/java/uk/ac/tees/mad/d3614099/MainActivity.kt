package uk.ac.tees.mad.d3614099

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import uk.ac.tees.mad.d3614099.presentation.RoommateFinderApp
import uk.ac.tees.mad.d3614099.ui.theme.RoommateFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RoommateFinderTheme(
                darkTheme = false
            ) {
                RoommateFinderApp()
            }
        }
    }
}

